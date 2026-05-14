package in.mai.yourproject.api.oraclepricewebhook;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;

public class OraclePriceWebhookService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public OraclePriceWebhookResponse createOraclePriceWebhook(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.TRACKER_PRICE;

        OraclePriceWebhookRequest request = new OraclePriceWebhookRequest(
                config.getOraclePriceWebhookName(),
                config.getOraclePriceReceiveUrl(),
                config.getWorkflowId(),
                "recurring/onceOnly",
                config.getOraclePriceSecret(),
                config.getOraclePriceContractAddress(),
                config.getOraclePriceChainId(),
                config.getOraclePrice()
        );

        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .auth().preemptive().basic("admin", "admin")
                .body(request)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(request), response);

        OraclePriceWebhookResponse oraclePriceResponse = response.as(OraclePriceWebhookResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, oraclePriceResponse);
        return oraclePriceResponse;
    }
}

