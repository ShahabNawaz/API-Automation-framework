package in.mai.yourproject.api.workflowchaincode;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;

public class WorkflowChaincodeService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public WorkflowChaincodeResponse getWorkflowChaincode(ITestContext context) {
        String url = config.getKwala_frontend_url() + String.format(ApiEndPoints.WORKFLOW_CHAINCODE, config.getWorkflowIdentifier());

        Response response = given()
                .relaxedHTTPSValidation()
                .log().all()
                .get(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "GET", response);

        WorkflowChaincodeResponse chaincodeResponse = response.as(WorkflowChaincodeResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, chaincodeResponse);
        return chaincodeResponse;
    }
}

