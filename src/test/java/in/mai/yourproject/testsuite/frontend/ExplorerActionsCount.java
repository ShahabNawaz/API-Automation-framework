package in.mai.yourproject.testsuite.frontend;

import in.mai.yourproject.api.exploreractionscount.ExplorerActionsCountResponse;
import in.mai.yourproject.api.exploreractionscount.ExplorerActionsCountService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ExplorerActionsCount extends in.mai.yourproject.api.common.MyAssert {

    @Description("To Get Explorer Actions Count")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void getExplorerActionsCount(ITestContext context){
        softAssert = new SoftAssert();

        ExplorerActionsCountResponse response = new ExplorerActionsCountService().getExplorerActionsCount(context);

        System.out.println("Explorer actions count: " + response.getCount());

        CommonAssert.softAssertOkay(response);
        softAssert.assertNotNull(response.getCount(), "Count should not be null");
        softAssert.assertTrue(response.getCount() >= 0, "Count should be non-negative");
        softAssert.assertAll();
    }
}

