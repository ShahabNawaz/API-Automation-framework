package in.mai.yourproject.api.exploreractionscount;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;

public class ExplorerActionsCountService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public ExplorerActionsCountResponse getExplorerActionsCount(ITestContext context) {
        String url = config.getKwala_frontend_url() + ApiEndPoints.EXPLORER_ACTIONS_COUNT;

        Response response = given()
                .relaxedHTTPSValidation()
                .log().all()
                .get(url);

        // Handle both JSON response with count field or plain number
        ExplorerActionsCountResponse countResponse;
        try {
            // Try to parse as JSON first
            countResponse = response.as(ExplorerActionsCountResponse.class, ObjectMapperType.GSON);
            // If count is null, try parsing the body as a plain number
            if (countResponse.getCount() == null) {
                String body = response.getBody().asString().trim();
                try {
                    countResponse.count = Long.parseLong(body);
                } catch (NumberFormatException e) {
                    // If it's JSON with different structure, try to extract count
                    countResponse.count = response.jsonPath().getLong("count");
                }
            }
        } catch (Exception e) {
            // If JSON parsing fails, treat as plain number
            countResponse = new ExplorerActionsCountResponse();
            String body = response.getBody().asString().trim();
            countResponse.count = Long.parseLong(body);
        }

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "GET", response);

        ApiUtil.updateResponse(response, countResponse);
        return countResponse;
    }
}

