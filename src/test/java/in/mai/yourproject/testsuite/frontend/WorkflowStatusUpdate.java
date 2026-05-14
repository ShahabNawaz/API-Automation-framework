package in.mai.yourproject.testsuite.frontend;

import in.mai.yourproject.api.workflowstatusupdate.WorkflowStatusUpdateResponse;
import in.mai.yourproject.api.workflowstatusupdate.WorkflowStatusUpdateService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WorkflowStatusUpdate extends in.mai.yourproject.api.common.MyAssert {

    @Description("To Update Workflow Status with status and workflow ID")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void updateWorkflowStatus(ITestContext context){
        softAssert = new SoftAssert();

        WorkflowStatusUpdateResponse response = new WorkflowStatusUpdateService().updateWorkflowStatus(context);

        System.out.println("Update workflow status success: " + response.isSuccess());

        CommonAssert.softAssertOkay(response);
        softAssert.assertTrue(response.isSuccess(), "Update workflow status should be successful");
        softAssert.assertAll();
    }
}

