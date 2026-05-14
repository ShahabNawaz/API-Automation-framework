package in.mai.yourproject.api.actionlog;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;

public class ActionLogSummaryService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public ActionLogSummaryResponse getActionLogSummary(ITestContext context) {
        String url = config.getKwala_frontend_url() + String.format(ApiEndPoints.ACTION_LOG_SUMMARY, config.getDeployerAddress());

        Response response = given()
                .relaxedHTTPSValidation()
                .log().all()
                .get(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "GET", response);

        ActionLogSummaryResponse summaryResponse = response.as(ActionLogSummaryResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, summaryResponse);
        return summaryResponse;
    }
}

