package in.mai.yourproject.api.deployestimate;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DeployEstimateService {
    Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public DeployEstimateResponse deployEstimate(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.DEPLOY_ESTIMATE;

        Map<String, String> constructorArgs = new HashMap<>();
        constructorArgs.put("x", "abc");
        constructorArgs.put("y", "2");

        DeployEstimateRequest deployEstimateRequest = new DeployEstimateRequest(config.getBytecode(),"ERC20",80002, constructorArgs,"",config.getAbiEncoded());

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(deployEstimateRequest)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(deployEstimateRequest), response);

        DeployEstimateResponse deployEstimateResponse = response.as(DeployEstimateResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, deployEstimateResponse);
        return deployEstimateResponse;
    }
}