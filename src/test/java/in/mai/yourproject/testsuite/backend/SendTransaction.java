package in.mai.yourproject.testsuite.backend;

import in.mai.yourproject.api.common.ITestContextKeys;
import in.mai.yourproject.api.common.MyAssert;
import in.mai.yourproject.api.sendtransactions.SendTransactionResponse;
import in.mai.yourproject.api.sendtransactions.SendTransactionService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SendTransaction extends MyAssert {

    @Description("Send Transaction with 2 args")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void sendTransaction(ITestContext context){
        softAssert = new SoftAssert();

        SendTransactionResponse response = new SendTransactionService().sendTransaction(context);

        context.setAttribute(ITestContextKeys.transaction, response.getTransaction());
        System.out.println("transaction: "+ context.getAttribute(ITestContextKeys.transaction));

        CommonAssert.softAssertOkay(response);
        softAssert.assertAll();

    }
}
