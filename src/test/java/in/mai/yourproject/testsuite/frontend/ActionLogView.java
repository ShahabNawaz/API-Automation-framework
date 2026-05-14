package in.mai.yourproject.testsuite.frontend;

import in.mai.yourproject.api.actionlog.ActionLog;
import in.mai.yourproject.api.actionlog.ActionLogResponse;
import in.mai.yourproject.api.actionlog.ActionLogService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ActionLogView extends in.mai.yourproject.api.common.MyAssert {

    @Description("To Get Action Logs for a deployer address")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void getActionLogs(ITestContext context){
        softAssert = new SoftAssert();

        ActionLogResponse response = new ActionLogService().getActionLogs(context);

        List<ActionLog> actionLogs = response.getActionLogs();
        System.out.println("Action logs count: " + (actionLogs != null ? actionLogs.size() : 0));
        
        if (actionLogs != null && !actionLogs.isEmpty()) {
            ActionLog firstLog = actionLogs.get(0);
            System.out.println("First log - ID: " + firstLog.getId());
            System.out.println("First log - Workflow ID: " + firstLog.getWorkflowId());
            System.out.println("First log - Action ID: " + firstLog.getActionId());
            System.out.println("First log - Execution Time: " + firstLog.getExecutionTime());
            System.out.println("First log - Chain ID: " + firstLog.getChainId());
            System.out.println("First log - Success: " + firstLog.isSuccess());
            System.out.println("First log - Retries: " + firstLog.getRetries());
            System.out.println("First log - Gas Fees: " + firstLog.getGasFees());
            System.out.println("First log - Created At: " + firstLog.getCreatedAt());
        }

        CommonAssert.softAssertOkay(response);
        softAssert.assertNotNull(response.getActionLogs(), "Action logs should not be null");
        softAssert.assertAll();
    }
}
