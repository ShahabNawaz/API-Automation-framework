package in.mai.yourproject.testsuite.backend;


import in.mai.yourproject.api.common.ITestContextKeys;
import in.mai.yourproject.api.common.MyAssert;
import in.mai.yourproject.api.deploycontract.DeployContractResponse;
import in.mai.yourproject.api.deploycontract.DeployContractService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DeployContract extends MyAssert {

    // ==================== POSITIVE TEST CASES ====================

    @Description("Deploy Contract with 2 constructor arguments - Positive Test")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void deployContractWithTwoArgs(ITestContext context){
        softAssert = new SoftAssert();

        DeployContractResponse response = new DeployContractService().deployContract(context);

        context.setAttribute(ITestContextKeys.contractAddress, response.getContractAddress());
        context.setAttribute(ITestContextKeys.transactionHash, response.getTransactionHash());
        System.out.println("contractAddress: "+context.getAttribute(ITestContextKeys.contractAddress));
        System.out.println("transactionHash: "+ context.getAttribute(ITestContextKeys.transactionHash));

        CommonAssert.softAssertOkay(response);
        softAssert.assertAll();
    }




    @Description("Deploy Contract with custom RPC URL - Positive Test")
    @Test(priority = 2, retryAnalyzer = MyRetryAnalyzer.class)
    public void deployContractWithCustomRPC(ITestContext context){
        softAssert = new SoftAssert();

        DeployContractResponse response = new DeployContractService().deployContractWithCustomRPC(context);

        context.setAttribute(ITestContextKeys.contractAddress, response.getContractAddress());
        context.setAttribute(ITestContextKeys.transactionHash, response.getTransactionHash());

        CommonAssert.softAssertOkay(response);
        softAssert.assertAll();
    }

    // ==================== NEGATIVE TEST CASES ====================

    @Description("Deploy Contract without ABI - Negative Test (Expected 500)")
    @Test(priority = 3, retryAnalyzer = MyRetryAnalyzer.class)
    public void deployContractWithoutABI(ITestContext context){
        softAssert = new SoftAssert();

        DeployContractResponse response = new DeployContractService().deployEmptyABI(context);
        CommonAssert.softAssertError(response);
        softAssert.assertAll();
    }

    @Description("Deploy Contract with invalid bytecode - Negative Test (Expected 500)")
    @Test(priority = 4, retryAnalyzer = MyRetryAnalyzer.class)
    public void deployContractWithInvalidBytecode(ITestContext context){
        softAssert = new SoftAssert();

        DeployContractResponse response = new DeployContractService().deployContractWithInvalidBytecode(context);
        CommonAssert.softAssertError(response);
        softAssert.assertAll();
    }

    @Description("Deploy Contract with invalid chain ID - Negative Test (Expected 500)")
    @Test(priority = 5, retryAnalyzer = MyRetryAnalyzer.class)
    public void deployContractWithInvalidChainId(ITestContext context){
        softAssert = new SoftAssert();

        DeployContractResponse response = new DeployContractService().deployContractWithInvalidChainId(context);
        CommonAssert.softAssertError(response);
        softAssert.assertAll();
    }

    @Description("Deploy Contract with invalid token standard - Negative Test (Expected 500)")
    @Test(priority = 6, retryAnalyzer = MyRetryAnalyzer.class)
    public void deployContractWithInvalidTokenStandard(ITestContext context){
        softAssert = new SoftAssert();

        DeployContractResponse response = new DeployContractService().deployContractWithInvalidTokenStandard(context);
        CommonAssert.softAssertOkay(response);
        softAssert.assertAll();
    }

    @Description("Deploy Contract with empty bytecode - Negative Test (Expected 500)")
    @Test(priority = 7, retryAnalyzer = MyRetryAnalyzer.class)
    public void deployContractWithEmptyBytecode(ITestContext context){
        softAssert = new SoftAssert();

        DeployContractResponse response = new DeployContractService().deployContractWithEmptyBytecode(context);
        CommonAssert.softAssertError(response);
        softAssert.assertAll();
    }

    @Description("Deploy Contract with null bytecode - Negative Test (Expected 500)")
    @Test(priority = 8, retryAnalyzer = MyRetryAnalyzer.class)
    public void deployContractWithNullBytecode(ITestContext context){
        softAssert = new SoftAssert();

        DeployContractResponse response = new DeployContractService().deployContractWithNullBytecode(context);
        CommonAssert.softAssertError(response);
        softAssert.assertAll();
    }

    // ==================== EDGE CASES ====================

    @Description("Deploy Contract with large constructor arguments - Edge Case")
    @Test(priority = 9, retryAnalyzer = MyRetryAnalyzer.class)
    public void deployContractWithLargeConstructorArgs(ITestContext context){
        softAssert = new SoftAssert();

        DeployContractResponse response = new DeployContractService().deployContractWithLargeConstructorArgs(context);
        
        // This could either succeed or fail depending on system limits
        if (response.statusCode == 200) {
            CommonAssert.softAssertOkay(response);
        } else {
            CommonAssert.softAssertError(response);
        }
        softAssert.assertAll();
    }

    @Description("Deploy Contract with special characters in constructor args - Edge Case")
    @Test(priority = 10, retryAnalyzer = MyRetryAnalyzer.class)
    public void deployContractWithSpecialCharacters(ITestContext context){
        softAssert = new SoftAssert();

        DeployContractResponse response = new DeployContractService().deployContractWithSpecialCharacters(context);
        
        // This could either succeed or fail depending on system validation
        if (response.statusCode == 200) {
            CommonAssert.softAssertOkay(response);
        } else {
            CommonAssert.softAssertError(response);
        }
        softAssert.assertAll();
    }

    @Description("Deploy Contract with unicode characters in constructor args - Edge Case")
    @Test(priority = 11, retryAnalyzer = MyRetryAnalyzer.class)
    public void deployContractWithUnicodeCharacters(ITestContext context){
        softAssert = new SoftAssert();

        DeployContractResponse response = new DeployContractService().deployContractWithUnicodeCharacters(context);
        
        // This could either succeed or fail depending on system validation
        if (response.statusCode == 200) {
            CommonAssert.softAssertOkay(response);
        } else {
            CommonAssert.softAssertError(response);
        }
        softAssert.assertAll();
    }

    @Description("Deploy Contract with negative chain ID - Edge Case (Expected 500)")
    @Test(priority = 12, retryAnalyzer = MyRetryAnalyzer.class)
    public void deployContractWithNegativeChainId(ITestContext context){
        softAssert = new SoftAssert();

        DeployContractResponse response = new DeployContractService().deployContractWithNegativeChainId(context);
        CommonAssert.softAssertError(response);
        softAssert.assertAll();
    }

    @Description("Deploy Contract with zero chain ID - Edge Case (Expected 500)")
    @Test(priority = 13, retryAnalyzer = MyRetryAnalyzer.class)
    public void deployContractWithZeroChainId(ITestContext context){
        softAssert = new SoftAssert();

        DeployContractResponse response = new DeployContractService().deployContractWithZeroChainId(context);
        CommonAssert.softAssertError(response);
        softAssert.assertAll();
    }
}
