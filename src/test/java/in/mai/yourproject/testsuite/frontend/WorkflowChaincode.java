package in.mai.yourproject.testsuite.frontend;

import in.mai.yourproject.api.workflowchaincode.WorkflowChaincodeResponse;
import in.mai.yourproject.api.workflowchaincode.WorkflowChaincodeService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WorkflowChaincode extends in.mai.yourproject.api.common.MyAssert {

    @Description("To Get Workflow Chaincode Address for a workflow identifier")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void getWorkflowChaincode(ITestContext context){
        softAssert = new SoftAssert();

        WorkflowChaincodeResponse response = new WorkflowChaincodeService().getWorkflowChaincode(context);

        System.out.println("Chaincode Address: " + response.getChaincodeAddress());

        CommonAssert.softAssertOkay(response);
        softAssert.assertNotNull(response.getChaincodeAddress(), "Chaincode address should not be null");
        softAssert.assertFalse(response.getChaincodeAddress().isEmpty(), "Chaincode address should not be empty");
        softAssert.assertAll();
    }
}

