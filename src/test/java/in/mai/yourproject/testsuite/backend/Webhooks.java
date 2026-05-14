package in.mai.yourproject.testsuite.backend;

import in.mai.yourproject.api.webhooks.WebhooksResponse;
import in.mai.yourproject.api.webhooks.WebhooksService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Webhooks extends in.mai.yourproject.api.common.MyAssert {

    @Description("To Create Webhook")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void createWebhook(ITestContext context){
        softAssert = new SoftAssert();

        WebhooksResponse response = new WebhooksService().createWebhook(context);

        System.out.println("Create webhook success: " + response.isSuccess());
        System.out.println("Subscription ID: " + response.getSubscriptionId());
        System.out.println("Created at: " + response.getCreatedAt());

        CommonAssert.softAssertOkay(response);
        softAssert.assertAll();
    }
}

