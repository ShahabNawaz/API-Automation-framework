package in.mai.yourproject.api.transactionsestimate;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TransactionEstimateService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public TransactionEstimateResponse estimateTransaction(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.TRANSACTION_ESTIMATE;

        List<String> args = new ArrayList<>();
        args.add("abc");
        args.add("2");
        TransactionEstimateRequest request = new TransactionEstimateRequest(
                config.getFunctionSignature(),
                args,
                config.getRpcUrl(),
                config.getTargetAddress(),
                config.getChainId()
        );

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(request)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(request), response);

        TransactionEstimateResponse estimateResponse = response.as(TransactionEstimateResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, estimateResponse);
        return estimateResponse;
    }
}

