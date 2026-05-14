package in.mai.yourproject.testsuite.backend;

import in.mai.yourproject.api.common.ITestContextKeys;
import in.mai.yourproject.api.common.MyAssert;
import in.mai.yourproject.api.transactionsestimate.TransactionEstimateResponse;
import in.mai.yourproject.api.transactionsestimate.TransactionEstimateService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TransactionEstimate extends MyAssert {

    @Description("To Get Estimation for Transaction with function signature")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void transactionEstimate(ITestContext context){
        softAssert = new SoftAssert();

        TransactionEstimateResponse response = new TransactionEstimateService().estimateTransaction(context);

        context.setAttribute(ITestContextKeys.transactionFee, response.getTransactionFee());
        context.setAttribute(ITestContextKeys.currency, response.getCurrency());
        System.out.println("transactionFee: "+context.getAttribute(ITestContextKeys.transactionFee));
        System.out.println("currency: "+ context.getAttribute(ITestContextKeys.currency));

        CommonAssert.softAssertOkay(response);
        softAssert.assertTrue(response.isSuccess(), "Transaction estimate should be successful");
        softAssert.assertNotNull(response.getTransactionFee(), "Transaction fee should not be null");
        softAssert.assertNotNull(response.getCurrency(), "Currency should not be null");
        softAssert.assertAll();

    }
}
