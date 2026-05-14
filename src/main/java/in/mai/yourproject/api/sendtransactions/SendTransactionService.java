package in.mai.yourproject.api.sendtransactions;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.api.common.ITestContextKeys;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SendTransactionService {

    Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public SendTransactionResponse sendTransaction(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.SEND_TRANSACTION;

        List<String> constructorArgs = new ArrayList<>();
        constructorArgs.add("abc");
        constructorArgs.add("2");

        SendTransactionRequest sendTransactionRequest = new SendTransactionRequest(constructorArgs, config.chainId, config.getFunctionSignature(), (String) context.getAttribute(ITestContextKeys.contractAddress),"");

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(sendTransactionRequest)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(sendTransactionRequest), response);

        SendTransactionResponse sendTransactionResponse = response.as(SendTransactionResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, sendTransactionResponse);
        return sendTransactionResponse;
    }
}
