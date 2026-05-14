package in.mai.yourproject.testsuite.frontend;

import in.mai.yourproject.api.workflowdetail.WorkflowDetailResponse;
import in.mai.yourproject.api.workflowdetail.WorkflowDetailService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WorkflowDetail extends in.mai.yourproject.api.common.MyAssert {

    @Description("To Get Workflow Detail with workflow identifier, deployer address, page and page size")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void getWorkflowDetail(ITestContext context){
        softAssert = new SoftAssert();

        WorkflowDetailResponse response = new WorkflowDetailService().getWorkflowDetail(context);

        System.out.println("Workflow Name: " + response.getWorkflowName());
        System.out.println("Workflow ID: " + response.getWorkflowId());
        System.out.println("Status: " + response.getStatus());
        System.out.println("Deployed: " + response.isDeployed());
        System.out.println("Repeat Every: " + response.getRepeatEvery());
        System.out.println("Execute After: " + response.getExecuteAfter());

        CommonAssert.softAssertOkay(response);
        softAssert.assertNotNull(response.getWorkflowName(), "Workflow name should not be null");
        softAssert.assertNotNull(response.getWorkflowId(), "Workflow ID should not be null");
        softAssert.assertAll();
    }
}

