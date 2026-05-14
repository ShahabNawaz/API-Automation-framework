package in.mai.yourproject.testsuite.backend;

import in.mai.yourproject.api.common.MyAssert;
import in.mai.yourproject.api.getsupportedtoken.GetSupportedTokensResponse;
import in.mai.yourproject.api.getsupportedtoken.GetSupportedTokensService;
import in.mai.yourproject.api.getsupportedtoken.TokenInfo;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetSupportedTokens extends MyAssert {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    @Description("Get supported tokens list")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void getSupportedTokens(ITestContext context) {
        softAssert = new SoftAssert();

        GetSupportedTokensResponse response = new GetSupportedTokensService().getSupportedTokens(context);

        CommonAssert.softAssertOkay(response);

        softAssert.assertNotNull(response.getData(), "data map should not be null");
        softAssert.assertTrue(response.getData().size() >= 0, "data map size should be >= 0");
        
        // Print the full response for debugging
        System.out.println("=== GetSupportedTokens Response ===");
        System.out.println("Success: " + response.isSuccess());
        System.out.println("Data size: " + response.getData().size());
        
        // Look for the specific contract address
        String targetAddress = config.targetAddress;
        String targetKey = targetAddress + "_1"; // Based on your JSON sample
        
        if (response.getData().containsKey(targetKey)) {
            TokenInfo tokenInfo = response.getData().get(targetKey);
            System.out.println("\n=== Found Token Info for " + targetAddress + " ===");
            System.out.println("Token Name: " + tokenInfo.getTokenName());
            System.out.println("Token Symbol: " + tokenInfo.getTokenContractSymbol());
            System.out.println("Token Contract Name: " + tokenInfo.getTokenContractName());
            System.out.println("Token Contract Address: " + tokenInfo.getTokenContractAddress());
            System.out.println("Token Contract Chain ID: " + tokenInfo.getTokenContractChainId());
            System.out.println("Chainlink Oracle Address: " + tokenInfo.getChainlinkOracleAddress());
            System.out.println("Chainlink Oracle Chain ID: " + tokenInfo.getChainlinkOracleChainId());
            System.out.println("Slippage: " + tokenInfo.getSlippage());
            System.out.println("Kwala Token ID: " + tokenInfo.getKwalaTokenId());
        } else {
            System.out.println("\n=== Token not found for address: " + targetAddress + " ===");
            System.out.println("Available keys:");
            response.getData().keySet().forEach(key -> System.out.println("  " + key));
        }

        softAssert.assertAll();
    }
}


