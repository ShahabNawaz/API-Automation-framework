package in.mai.yourproject.testsuite.frontend;

import in.mai.yourproject.api.actionlog.ActionLogSummaryResponse;
import in.mai.yourproject.api.actionlog.ActionLogSummaryService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ActionLogSummaryView extends in.mai.yourproject.api.common.MyAssert {

    @Description("To Get Action Log Summary for a deployer address")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void getActionLogSummary(ITestContext context){
        softAssert = new SoftAssert();

        ActionLogSummaryResponse response = new ActionLogSummaryService().getActionLogSummary(context);

        System.out.println("Average Execution Time (ms): " + response.getAverageExecutionTimeMs());
        System.out.println("Failed Count: " + response.getFailedCount());
        System.out.println("Successful Count: " + response.getSuccessfulCount());
        
        List<String> chainsUsed = response.getChainsUsed();
        System.out.println("Chains Used: " + (chainsUsed != null ? chainsUsed.toString() : "[]"));
        System.out.println("Chains Used Count: " + (chainsUsed != null ? chainsUsed.size() : 0));

        CommonAssert.softAssertOkay(response);
        softAssert.assertNotNull(response, "Action log summary response should not be null");
        softAssert.assertTrue(response.getAverageExecutionTimeMs() >= 0, "Average execution time should be non-negative");
        softAssert.assertTrue(response.getFailedCount() >= 0, "Failed count should be non-negative");
        softAssert.assertTrue(response.getSuccessfulCount() >= 0, "Successful count should be non-negative");
        softAssert.assertNotNull(response.getChainsUsed(), "Chains used should not be null");
        softAssert.assertAll();
    }
}

