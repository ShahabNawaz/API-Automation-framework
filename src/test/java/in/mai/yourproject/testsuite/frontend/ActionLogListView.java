package in.mai.yourproject.testsuite.frontend;

import in.mai.yourproject.api.actionlog.ActionLog;
import in.mai.yourproject.api.actionlog.ActionLogListService;
import in.mai.yourproject.api.actionlog.ActionLogResponse;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ActionLogListView extends in.mai.yourproject.api.common.MyAssert {

    @Description("To Get Action Log List for a deployer address")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void getActionLogList(ITestContext context){
        softAssert = new SoftAssert();

        ActionLogResponse response = new ActionLogListService().getActionLogList(context);

        List<ActionLog> actionLogs = response.getActionLogs();
        System.out.println("Action logs count: " + (actionLogs != null ? actionLogs.size() : 0));
        
        if (actionLogs != null && !actionLogs.isEmpty()) {
            System.out.println("Total action logs retrieved: " + actionLogs.size());
            
            // Print details of first few logs
            int logsToPrint = Math.min(5, actionLogs.size());
            for (int i = 0; i < logsToPrint; i++) {
                ActionLog log = actionLogs.get(i);
                System.out.println("Log " + (i + 1) + " - ID: " + log.getId() + 
                    ", Workflow ID: " + log.getWorkflowId() + 
                    ", Action ID: " + log.getActionId() + 
                    ", Success: " + log.isSuccess() + 
                    ", Execution Time: " + log.getExecutionTime());
            }
            
            // Print summary statistics
            long successfulCount = actionLogs.stream().filter(ActionLog::isSuccess).count();
            long failedCount = actionLogs.size() - successfulCount;
            System.out.println("Summary - Successful: " + successfulCount + ", Failed: " + failedCount);
        }

        CommonAssert.softAssertOkay(response);
        softAssert.assertNotNull(response.getActionLogs(), "Action logs should not be null");
        softAssert.assertAll();
    }
}

