package in.mai.yourproject.api.gasfeeslogs;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;

public class GasFeesLogsService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public GasFeesLogsResponse getGasFeesLogs(ITestContext context) {
        String url = config.getKwala_frontend_url() + String.format(ApiEndPoints.GAS_FEES_LOGS_VIEW, config.getGasFeesLogsAddress());

        Response response = given()
                .relaxedHTTPSValidation()
                .log().all()
                .get(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "GET", response);

        GasFeesLogsResponse gasFeesLogsResponse = response.as(GasFeesLogsResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, gasFeesLogsResponse);
        return gasFeesLogsResponse;
    }
}

