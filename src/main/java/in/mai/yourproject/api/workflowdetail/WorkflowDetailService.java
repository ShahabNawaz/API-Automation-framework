package in.mai.yourproject.api.workflowdetail;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;

public class WorkflowDetailService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public WorkflowDetailResponse getWorkflowDetail(ITestContext context) {
        String url = config.getKwala_frontend_url() + String.format(ApiEndPoints.WORKFLOW_DETAIL, 
                config.getWorkflowIdentifier(), config.getDeployerAddress());

        Response response = given()
                .relaxedHTTPSValidation()
                .log().all()
                .queryParam("page", config.getPage())
                .queryParam("page_size", config.getPageSize())
                .get(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "GET", response);

        WorkflowDetailResponse detailResponse = response.as(WorkflowDetailResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, detailResponse);
        return detailResponse;
    }
}

