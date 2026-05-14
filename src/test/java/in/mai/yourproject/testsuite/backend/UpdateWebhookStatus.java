package in.mai.yourproject.testsuite.backend;

import in.mai.yourproject.api.updatewebhookstatus.UpdateWebhookStatusResponse;
import in.mai.yourproject.api.updatewebhookstatus.UpdateWebhookStatusService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdateWebhookStatus extends in.mai.yourproject.api.common.MyAssert {

    @Description("To Update Webhook Status with subscription ID and status")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void updateWebhookStatus(ITestContext context){
        softAssert = new SoftAssert();

        UpdateWebhookStatusResponse response = new UpdateWebhookStatusService().updateWebhookStatus(context);

        System.out.println("Update webhook status success: " + response.isSuccess());
        System.out.println("Update webhook status message: " + response.getMessage());

        CommonAssert.softAssertOkay(response);
//        softAssert.assertTrue(response.isSuccess(), "Update webhook status should be successful");
//        softAssert.assertNotNull(response.getMessage(), "Message should not be null");
        softAssert.assertAll();
    }
}

