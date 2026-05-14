package in.mai.yourproject.api.workflowverify;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class WorkflowVerifyService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public WorkflowVerifyResponse verifyWorkflow(Map<String, Object> requestBody) {
        String url = config.getKwala_frontend_url() + ApiEndPoints.WORKFLOW_VERIFY;

        Response response = given()
                .relaxedHTTPSValidation()
                .log().all()
                .contentType(ContentType.JSON)
                .headers(buildDefaultHeaders())
                .body(requestBody)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(requestBody), response);

        WorkflowVerifyResponse workflowVerifyResponse = response.as(WorkflowVerifyResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, workflowVerifyResponse);
        return workflowVerifyResponse;
    }

    private Map<String, Object> buildDefaultHeaders() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("accept", "*/*");
        headers.put("accept-language", "en-GB,en;q=0.9");
        headers.put("cache-control", "no-cache");
        headers.put("origin", config.getKwala_frontend_url());
        headers.put("pragma", "no-cache");
        headers.put("referer", config.getKwala_frontend_url() + "/");
        headers.put("sec-fetch-dest", "empty");
        headers.put("sec-fetch-mode", "cors");
        headers.put("sec-fetch-site", "cross-site");
        headers.put("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) "
                + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Safari/537.36");
        return headers;
    }
}

