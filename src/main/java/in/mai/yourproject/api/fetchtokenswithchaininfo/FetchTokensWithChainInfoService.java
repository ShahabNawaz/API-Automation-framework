package in.mai.yourproject.api.fetchtokenswithchaininfo;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;

public class FetchTokensWithChainInfoService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public FetchTokensWithChainInfoResponse fetchTokensWithChainInfo(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.FETCH_TOKENS_WITH_CHAIN_INFO;

        Response response = given()
                .relaxedHTTPSValidation()
                .log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .queryParam("tokensInfo", config.isTokensInfo())
                .queryParam("mainnetChains", config.isMainnetChains())
                .queryParam("testnetChains", config.isTestnetChains())
                .get(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "GET", response);

        FetchTokensWithChainInfoResponse fetchResponse = response.as(FetchTokensWithChainInfoResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, fetchResponse);
        return fetchResponse;
    }
}

