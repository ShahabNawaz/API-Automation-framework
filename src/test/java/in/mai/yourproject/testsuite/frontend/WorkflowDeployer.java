package in.mai.yourproject.testsuite.frontend;

import in.mai.yourproject.api.workflowdeployer.WorkflowDeployerResponse;
import in.mai.yourproject.api.workflowdeployer.WorkflowDeployerService;
import in.mai.yourproject.api.workflowdeployer.WorkflowInfo;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class WorkflowDeployer extends in.mai.yourproject.api.common.MyAssert {

    @Description("To Get Workflow Deployer with deployer address, page and page size")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void getWorkflowDeployer(ITestContext context){
        softAssert = new SoftAssert();

        WorkflowDeployerResponse response = new WorkflowDeployerService().getWorkflowDeployer(context);

        System.out.println("Total workflows: " + response.getTotalWorkflows());
        
        List<WorkflowInfo> workflows = response.getWorkflows();
        System.out.println("Workflows count: " + (workflows != null ? workflows.size() : 0));
        
        if (workflows != null && !workflows.isEmpty()) {
            WorkflowInfo firstWorkflow = workflows.get(0);
            System.out.println("First workflow - Name: " + firstWorkflow.getWorkflowName());
            System.out.println("First workflow - ID: " + firstWorkflow.getWorkflowId());
            System.out.println("First workflow - Status: " + firstWorkflow.getStatus());
            System.out.println("First workflow - Deployed: " + firstWorkflow.isDeployed());
        }

        CommonAssert.softAssertOkay(response);
        softAssert.assertNotNull(response.getWorkflows(), "Workflows should not be null");
        softAssert.assertTrue(response.getTotalWorkflows() >= 0, "Total workflows should be non-negative");
        softAssert.assertAll();
    }
}

