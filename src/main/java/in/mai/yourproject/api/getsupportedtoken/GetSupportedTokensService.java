package in.mai.yourproject.api.getsupportedtoken;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;

public class GetSupportedTokensService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public GetSupportedTokensResponse getSupportedTokens(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.GET_SUPPORTED_TOKENS;

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .get(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "GET", response);

        GetSupportedTokensResponse tokensResponse = response.as(GetSupportedTokensResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, tokensResponse);
        return tokensResponse;
    }
}
