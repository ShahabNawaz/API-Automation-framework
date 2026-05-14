package in.mai.yourproject.testsuite.backend;

import in.mai.yourproject.api.blockmonitor.BlockMonitorResponse;
import in.mai.yourproject.api.blockmonitor.BlockMonitorService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class BlockMonitor extends in.mai.yourproject.api.common.MyAssert {

    @Description("To Monitor Block with target block number and chain ID")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void blockMonitor(ITestContext context){
        softAssert = new SoftAssert();

        BlockMonitorResponse response = new BlockMonitorService().monitorBlock(context);

        System.out.println("Block monitor success: " + response.isSuccess());

        CommonAssert.softAssertOkay(response);
        softAssert.assertTrue(response.isSuccess(), "Block monitor should be successful");
        softAssert.assertAll();
    }
}
