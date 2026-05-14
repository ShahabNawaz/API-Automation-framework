package in.mai.yourproject.testsuite.backend;

import in.mai.yourproject.api.oraclepricewebhook.OraclePriceWebhookResponse;
import in.mai.yourproject.api.oraclepricewebhook.OraclePriceWebhookService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OraclePriceWebhook extends in.mai.yourproject.api.common.MyAssert {

    @Description("To Create Oracle Price Webhook with name, oracle price receive URL, secret, contract address, chain ID and price")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void createOraclePriceWebhook(ITestContext context){
        softAssert = new SoftAssert();

        OraclePriceWebhookResponse response = new OraclePriceWebhookService().createOraclePriceWebhook(context);

        System.out.println("Create oracle price webhook success: " + response.isSuccess());
        System.out.println("Subscription ID: " + response.getSubscriptionId());
        System.out.println("Created at: " + response.getCreatedAt());

        CommonAssert.softAssertOkay(response);
        softAssert.assertTrue(response.isSuccess(), "Create oracle price webhook should be successful");
        softAssert.assertNotNull(response.getSubscriptionId(), "Subscription ID should not be null");
        softAssert.assertNotNull(response.getCreatedAt(), "Created at should not be null");
        softAssert.assertAll();
    }
}