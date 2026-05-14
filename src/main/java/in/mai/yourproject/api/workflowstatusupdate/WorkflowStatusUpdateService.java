package in.mai.yourproject.api.workflowstatusupdate;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;

public class WorkflowStatusUpdateService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public WorkflowStatusUpdateResponse updateWorkflowStatus(ITestContext context) {
        String url = config.getKwala_frontend_url() + ApiEndPoints.WORKFLOW_STATUS_UPDATE;

        WorkflowStatusUpdateRequest request = new WorkflowStatusUpdateRequest(
                config.getWorkflowStatus(),
                config.getWorkflowId()
        );

        Response response = given()
                .relaxedHTTPSValidation()
                .log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(request), response);

        WorkflowStatusUpdateResponse updateResponse = response.as(WorkflowStatusUpdateResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, updateResponse);
        return updateResponse;
    }
}

