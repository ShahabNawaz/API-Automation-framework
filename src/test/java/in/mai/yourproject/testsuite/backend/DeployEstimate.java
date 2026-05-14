package in.mai.yourproject.testsuite.backend;

import in.mai.yourproject.api.common.ITestContextKeys;
import in.mai.yourproject.api.common.MyAssert;
import in.mai.yourproject.api.deployestimate.DeployEstimateResponse;
import in.mai.yourproject.api.deployestimate.DeployEstimateService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DeployEstimate extends MyAssert {

    @Description("To Get Estimation for deploying  Contract with 2 args")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void deployEstimate(ITestContext context){
        softAssert = new SoftAssert();

        DeployEstimateResponse response = new DeployEstimateService().deployEstimate(context);

        context.setAttribute(ITestContextKeys.transactionFee, response.getTransactionFee());
        context.setAttribute(ITestContextKeys.currency, response.getCurrency());
        System.out.println("transactionFee: "+context.getAttribute(ITestContextKeys.transactionFee));
        System.out.println("currency: "+ context.getAttribute(ITestContextKeys.currency));

        CommonAssert.softAssertOkay(response);
        softAssert.assertAll();

    }
}
