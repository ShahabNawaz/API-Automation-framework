package in.mai.yourproject.testsuite.frontend;

import in.mai.yourproject.api.common.MyAssert;
import in.mai.yourproject.api.workflowstatus.WorkflowStatusResponse;
import in.mai.yourproject.api.workflowstatus.WorkflowStatusService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WorkflowStatus extends MyAssert {

    private WorkflowStatusService workflowStatusService;
    private Config config;

    @BeforeMethod
    public void setUp() {
        workflowStatusService = new WorkflowStatusService();
        config = ConfigManager.CONFIG_MANAGER.getConfig();
        softAssert = new SoftAssert();
    }

    @Description("Workflow status should be WORKFLOW_DEPLOYED for a deployed workflow")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void verifyWorkflowStatusIsDeployed() {
        WorkflowStatusResponse response = workflowStatusService.getWorkflowStatus(config.getWorkflowIdentifier());
        softAssert.assertEquals(response.getStatus(), "WORKFLOW_DEPLOYED", "Workflow should be deployed");
        CommonAssert.softAssertOkay(response);
        softAssert.assertAll();
    }

    @Description("Workflow status request with an unknown identifier should return an error")
    @Test(priority = 2, retryAnalyzer = MyRetryAnalyzer.class)
    public void verifyWorkflowStatusWithUnknownIdentifier() {
        String invalidWorkflowIdentifier = config.getWorkflowIdentifier() + "-invalid";

        WorkflowStatusResponse response = workflowStatusService.getWorkflowStatus(invalidWorkflowIdentifier);

        softAssert.assertTrue(response.statusCode >= 200,
                "Status code should indicate a client or server error for unknown workflow identifier");
        softAssert.assertTrue(response.getStatus() == null || response.getStatus().isEmpty(),
                "Workflow status should be empty for unknown workflow identifier");
        softAssert.assertAll();
    }
}
