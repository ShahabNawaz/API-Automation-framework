package in.mai.yourproject.testsuite.backend;

import in.mai.yourproject.api.fetchtokenswithchaininfo.ChainData;
import in.mai.yourproject.api.fetchtokenswithchaininfo.FetchTokensWithChainInfoResponse;
import in.mai.yourproject.api.fetchtokenswithchaininfo.FetchTokensWithChainInfoService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FetchTokensWithChainInfo extends in.mai.yourproject.api.common.MyAssert {

    @Description("To Fetch Tokens With Chain Info")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void fetchTokensWithChainInfo(ITestContext context){
        softAssert = new SoftAssert();

        FetchTokensWithChainInfoResponse response = new FetchTokensWithChainInfoService().fetchTokensWithChainInfo(context);

        System.out.println("Fetch tokens with chain info success: " + response.isSuccess());
        
        ChainData data = response.getData();
        if (data != null) {
            System.out.println("Mainnet chains count: " + (data.getMainnetChains() != null ? data.getMainnetChains().size() : 0));
            System.out.println("Testnet chains count: " + (data.getTestnetChains() != null ? data.getTestnetChains().size() : 0));
        }

        CommonAssert.softAssertOkay(response);
        softAssert.assertTrue(response.isSuccess(), "Fetch tokens with chain info should be successful");
        softAssert.assertNotNull(response.getData(), "Data should not be null");
        softAssert.assertAll();
    }
}

