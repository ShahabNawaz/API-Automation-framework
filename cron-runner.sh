#!/bin/bash

# --- Configuration ---
: "${AWS_REGION:=ap-south-1}"
: "${S3_BUCKET:=prod-kwala-pipeline-allure}"
: "${CLOUDFRONT_ID:=E2PMWCPDXGW9SK}"
: "${REPORT_DIR:=allure-report}"
: "${RESULT_DIR:=target/allure-results}"
: "${LOCAL_REPORT_EXPORT_DIR:=}"

# Email Config
SENDER="kwala-automation@kalp.network"

RECIPIENTS='"mohit.gupta@mai.io", "shahab.nawaz@kalp.studio", "arun.tyagi@mai.io", "satheesh.srinivasan@mai.io", "ritesh.bhatt@mai.io", "Rajat.Bahuguna@kalp.studio", "Shubham.Raj@mai.io", "shubham.agarwal@mai.io",
            "Chirag.Garg@mai.io", "Mudit.Saxena@mai.io", "tushar.bansal@mai.io", "aamir.iqbal@mai.io"'

REPORT_URL="https://allure-report.kalp.network/"

echo "=== 🚀 Starting Automated QA Run: $(date) ==="
cd /app

# 1. Download History
echo "📥 Downloading Allure history..."
mkdir -p "${RESULT_DIR}/history"
set +e
timeout 30s aws s3 sync "s3://${S3_BUCKET}/history/" "${RESULT_DIR}/history/" --region "${AWS_REGION}" --no-cli-pager || echo "⚠️ S3 Sync failed (ignoring)..."

# 2. Run Tests (Capture Output)
echo "🧪 Running Tests..."
# Redirect stderr to stdout so we capture everything in mvn.log
mvn clean test -Dsurefire.suiteXmlFiles=testng.xml > mvn.log 2>&1
TEST_EXIT_CODE=$?
set -e 

echo "-------------------------------------------------------"
echo "✅ Maven Process Finished with Exit Code: $TEST_EXIT_CODE"

# 3. Determine Status Color
if [ $TEST_EXIT_CODE -eq 0 ]; then
    STATUS="✅ PASSED"
    COLOR="#28a745" # Green
else
    STATUS="❌ FAILED"
    COLOR="#dc3545" # Red
fi

# 4. Generate Report
echo "📊 Generating Report..."
allure generate ${RESULT_DIR} --clean -o ${REPORT_DIR} || echo "⚠️ Allure gen failed"

# 5. Read counts from generated Allure summary (exact report numbers)
echo "🔍 Reading Allure summary counts..."
SUMMARY_JSON="${REPORT_DIR}/widgets/summary.json"
if [ -f "$SUMMARY_JSON" ]; then
    METHOD_PASS=$(jq -r '.statistic.passed // 0' "$SUMMARY_JSON")
    METHOD_SKIP=$(jq -r '.statistic.skipped // 0' "$SUMMARY_JSON")
    METHOD_TOTAL=$(jq -r '.statistic.total // 0' "$SUMMARY_JSON")
    # Allure tracks failed and broken separately; email uses a combined Failed count.
    METHOD_FAIL=$(jq -r '(.statistic.failed // 0) + (.statistic.broken // 0)' "$SUMMARY_JSON")
else
    echo "⚠️ Allure summary missing. Defaulting counts to 0."
    METHOD_TOTAL=0; METHOD_FAIL=0; METHOD_PASS=0; METHOD_SKIP=0
fi

echo "📊 Allure Stats -> Pass: $METHOD_PASS | Fail: $METHOD_FAIL | Skip: $METHOD_SKIP | Total: $METHOD_TOTAL"

# 6. Upload History & Report (Sync)
echo "📤 Uploading Report to S3..."
aws s3 sync ${REPORT_DIR}/ s3://${S3_BUCKET}/ --region ${AWS_REGION} --delete --no-cli-pager || echo "⚠️ S3 Upload Failed"


# 7. Invalidate CloudFront (RESTORED STEP)

echo "🔄 Invalidating Cache..."

aws cloudfront create-invalidation --distribution-id ${CLOUDFRONT_ID} --paths "/*" --no-cli-pager || echo "⚠️ CloudFront Invalidation Failed (Check IAM Permissions)"

# 8. Send Email (Original Format)
echo "📧 Sending Notification..."

HTML_BODY="<html><body style='font-family: Arial, sans-serif;'>
<h2>Test Run Complete</h2>
<p><b>Status:</b> <span style='color:${COLOR}; font-weight:bold;'>${STATUS}</span></p>

<div style='background-color: #f8f9fa; padding: 15px; border-radius: 5px; width: fit-content; border: 1px solid #ddd;'>
    <p style='margin: 5px 0; font-size: 1.1em;'><b>📊 Summary:</b></p>
    <ul style='list-style-type: none; padding-left: 0;'>
        <li style='color: #28a745; margin-bottom: 5px;'>✅ <b>Passed:</b> ${METHOD_PASS}</li>
        <li style='color: #dc3545; margin-bottom: 5px;'>❌ <b>Failed:</b> ${METHOD_FAIL}</li>
        <li style='color: #ffc107; margin-bottom: 5px;'>⚠️ <b>Skipped:</b> ${METHOD_SKIP}</li>
        <li style='border-top: 1px solid #ccc; margin-top: 8px; padding-top: 8px;'><b>Total:</b> ${METHOD_TOTAL}</li>
    </ul>
</div>

<p><b>Date:</b> $(date)</p>
<p><b>Report:</b> <a href='${REPORT_URL}' style='background-color: #007bff; color: white; padding: 10px 15px; text-decoration: none; border-radius: 5px; display: inline-block; margin-top: 10px;'>View Full Allure Report</a></p>
<p style='font-size: small; color: gray; margin-top: 20px;'><i>(Counts reflect generated Allure report summary)</i></p>
</body></html>"

# Parse recipients robustly (supports commas, spaces, quotes, and newlines)
TO_ADDRESSES_JSON=$(jq -cn --arg to "$RECIPIENTS" '
  $to
  | gsub("[\\r\\n\\t]"; " ")
  | gsub(";"; ",")
  | split(",")
  | map(gsub("^\\s+|\\s+$"; ""))
  | map(gsub("^\"|\"$"; ""))
  | map(select(length > 0))
')

# Use jq to build JSON safely (handling quotes in HTML)
jq -n \
  --arg source "$SENDER" \
  --argjson toAddresses "$TO_ADDRESSES_JSON" \
  --arg subject "Kwala API Automation | ${STATUS} (${METHOD_PASS}/${METHOD_TOTAL})" \
  --arg html "$HTML_BODY" \
  '{
    Source: $source,
    Destination: { ToAddresses: $toAddresses },
    Message: {
      Subject: { Data: $subject, Charset: "UTF-8" },
      Body: { Html: { Data: $html, Charset: "UTF-8" } }
    }
  }' > email.json

aws ses send-email --cli-input-json file://email.json --region ${AWS_REGION} --no-cli-pager || echo "⚠️ Email Sending Failed"

exit $TEST_EXIT_CODE