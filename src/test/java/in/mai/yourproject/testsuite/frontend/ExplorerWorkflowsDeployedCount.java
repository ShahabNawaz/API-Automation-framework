package in.mai.yourproject.testsuite.frontend;

import in.mai.yourproject.api.explorerworkflowsdeployedcount.ExplorerWorkflowsDeployedCountResponse;
import in.mai.yourproject.api.explorerworkflowsdeployedcount.ExplorerWorkflowsDeployedCountService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ExplorerWorkflowsDeployedCount extends in.mai.yourproject.api.common.MyAssert {

    @Description("To Get Explorer Workflows Deployed Count")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void getExplorerWorkflowsDeployedCount(ITestContext context){
        softAssert = new SoftAssert();

        ExplorerWorkflowsDeployedCountResponse response = new ExplorerWorkflowsDeployedCountService().getExplorerWorkflowsDeployedCount(context);

        System.out.println("Explorer workflows deployed count: " + response.getCount());

        CommonAssert.softAssertOkay(response);
        softAssert.assertNotNull(response.getCount(), "Count should not be null");
        softAssert.assertTrue(response.getCount() >= 0, "Count should be non-negative");
        softAssert.assertAll();
    }
}

