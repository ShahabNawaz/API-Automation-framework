package in.mai.yourproject.testsuite.frontend;

import in.mai.yourproject.api.common.MyAssert;
import in.mai.yourproject.api.workflowverify.WorkflowVerifyResponse;
import in.mai.yourproject.api.workflowverify.WorkflowVerifyService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import in.mai.yourproject.common.util.WorkflowPayloadHelper;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class WorkflowVerify extends MyAssert {

    private static final String WORKFLOW_PAYLOAD_PATH = "valid_workflow_request.json";

    @Description("Workflow verify with complete YAML should pass syntax and schema validation")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void verifyWorkflowWithValidYaml() {
        WorkflowVerifyService workflowVerifyService = new WorkflowVerifyService();
        softAssert = new SoftAssert();
        Map<String, Object> validPayloadTemplate = WorkflowPayloadHelper.loadPayload(WORKFLOW_PAYLOAD_PATH);
        Map<String, Object> requestBody = WorkflowPayloadHelper.deepCopy(validPayloadTemplate);

        WorkflowVerifyResponse response = workflowVerifyService.verifyWorkflow(requestBody);

        CommonAssert.softAssertOkay(response);
        softAssert.assertTrue(response.isSyntaxCheck(), "Syntax check should be true");
        softAssert.assertTrue(response.isSchemaValidation(), "Schema validation should be true");
        softAssert.assertTrue(response.getError() == null || response.getError().isEmpty(),
                "Error message should be empty");

        softAssert.assertAll();
    }

    @Description("Workflow verify should flag schema error when workflow name is missing")
    @Test(priority = 2, retryAnalyzer = MyRetryAnalyzer.class)
    public void verifyWorkflowMissingName() {
        WorkflowVerifyService workflowVerifyService = new WorkflowVerifyService();
        softAssert = new SoftAssert();
        Map<String, Object> validPayloadTemplate = WorkflowPayloadHelper.loadPayload(WORKFLOW_PAYLOAD_PATH);
        Map<String, Object> requestBody = WorkflowPayloadHelper.deepCopy(validPayloadTemplate);
        String yaml = (String) requestBody.get("yaml");
        requestBody.put("yaml", WorkflowPayloadHelper.removeWorkflowName(yaml));

        WorkflowVerifyResponse response = workflowVerifyService.verifyWorkflow(requestBody);

        CommonAssert.softAssertBadRequest(response);
        softAssert.assertAll();
    }
}
