package in.mai.yourproject.api.workflowstatus;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class WorkflowStatusService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public WorkflowStatusResponse getWorkflowStatus(String workflowIdentifier) {
        String encodedIdentifier = URLEncoder.encode(workflowIdentifier, StandardCharsets.UTF_8);
        String url = config.getKwala_frontend_url() + String.format(ApiEndPoints.WORKFLOW_STATUS, encodedIdentifier);

        Response response = given()
                .relaxedHTTPSValidation()
                .log().all()
                .get(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "GET", response);

        WorkflowStatusResponse workflowStatusResponse = response.as(WorkflowStatusResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, workflowStatusResponse);
        return workflowStatusResponse;
    }
}


