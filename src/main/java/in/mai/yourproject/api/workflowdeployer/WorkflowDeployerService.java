package in.mai.yourproject.api.workflowdeployer;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;

public class WorkflowDeployerService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public WorkflowDeployerResponse getWorkflowDeployer(ITestContext context) {
        String url = config.getKwala_frontend_url() + String.format(ApiEndPoints.WORKFLOW_DEPLOYER, config.getDeployerAddress());

        Response response = given()
                .relaxedHTTPSValidation()
                .log().all()
                .queryParam("page", config.getPage())
                .queryParam("page_size", config.getPageSize())
                .get(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "GET", response);

        WorkflowDeployerResponse deployerResponse = response.as(WorkflowDeployerResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, deployerResponse);
        return deployerResponse;
    }
}

