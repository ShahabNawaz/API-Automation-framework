package in.mai.yourproject.api.exploreractions;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;

public class ExplorerActionsService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public ExplorerActionsResponse getExplorerActions(ITestContext context) {
        String url = config.getKwala_frontend_url() + ApiEndPoints.EXPLORER_ACTIONS;

        Response response = given()
                .relaxedHTTPSValidation()
                .log().all()
                .get(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "GET", response);

        ExplorerActionsResponse explorerActionsResponse = response.as(ExplorerActionsResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, explorerActionsResponse);
        return explorerActionsResponse;
    }
}

