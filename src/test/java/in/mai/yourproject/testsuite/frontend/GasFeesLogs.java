package in.mai.yourproject.testsuite.frontend;

import in.mai.yourproject.api.gasfeeslogs.GasFeesLog;
import in.mai.yourproject.api.gasfeeslogs.GasFeesLogsResponse;
import in.mai.yourproject.api.gasfeeslogs.GasFeesLogsService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class GasFeesLogs extends in.mai.yourproject.api.common.MyAssert {

    @Description("To Get Gas Fees Logs for an address")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void getGasFeesLogs(ITestContext context){
        softAssert = new SoftAssert();

        GasFeesLogsResponse response = new GasFeesLogsService().getGasFeesLogs(context);

        List<GasFeesLog> gasFeesLogs = response.getGasFeesLogs();
        System.out.println("Gas fees logs count: " + (gasFeesLogs != null ? gasFeesLogs.size() : 0));
        
        if (gasFeesLogs != null && !gasFeesLogs.isEmpty()) {
            GasFeesLog firstLog = gasFeesLogs.get(0);
            System.out.println("First log - From: " + firstLog.getFrom());
            System.out.println("First log - Gas: " + firstLog.getGas());
            System.out.println("First log - TxID: " + firstLog.getTxID());
            System.out.println("First log - Workflow ID: " + firstLog.getWorkflowId());
        }

        CommonAssert.softAssertOkay(response);
        softAssert.assertAll();
    }
}

