package in.mai.yourproject.testsuite.backend;

import in.mai.yourproject.api.common.MyAssert;
import in.mai.yourproject.api.getsupportedchains.ChainInfo;
import in.mai.yourproject.api.getsupportedchains.GetSupportedChainsResponse;
import in.mai.yourproject.api.getsupportedchains.GetSupportedChainsService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class GetSupportedChains extends MyAssert {

    @Description("Get supported chains list")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void getSupportedChains() {
        softAssert = new SoftAssert();

        GetSupportedChainsResponse response = new GetSupportedChainsService().getSupportedChains();

        CommonAssert.softAssertOkay(response);

        softAssert.assertTrue(response.isSuccess(), "Success should be true");
        softAssert.assertNotNull(response.getData(), "Data map should not be null");
        softAssert.assertTrue(response.getData().size() > 0, "Data map should contain at least one chain");

        // Print the full response for debugging
        System.out.println("=== GetSupportedChains Response ===");
        System.out.println("Success: " + response.isSuccess());
        System.out.println("Number of chains: " + response.getData().size());

        // Validate each chain has required fields
        for (Map.Entry<String, ChainInfo> entry : response.getData().entrySet()) {
            String chainId = entry.getKey();
            ChainInfo chainInfo = entry.getValue();

            System.out.println("\n=== Chain ID: " + chainId + " ===");
            System.out.println("Chain Name: " + chainInfo.getChainName());
            System.out.println("Gas Token Primary Key: " + chainInfo.getGasTokenPrimaryKey());
            System.out.println("Currency: " + chainInfo.getCurrency());

            softAssert.assertNotNull(chainInfo.getChainName(), "Chain name should not be null for chain ID: " + chainId);
            softAssert.assertNotNull(chainInfo.getCurrency(), "Currency should not be null for chain ID: " + chainId);
            softAssert.assertFalse(chainInfo.getChainName().isEmpty(), "Chain name should not be empty for chain ID: " + chainId);
            softAssert.assertFalse(chainInfo.getCurrency().isEmpty(), "Currency should not be empty for chain ID: " + chainId);
        }

        softAssert.assertAll();
    }
}

