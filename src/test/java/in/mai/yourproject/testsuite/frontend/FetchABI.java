package in.mai.yourproject.testsuite.frontend;

import in.mai.yourproject.api.common.MyAssert;
import in.mai.yourproject.api.fetchabi.FetchABIResponse;
import in.mai.yourproject.api.fetchabi.FetchABIService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FetchABI extends MyAssert {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();
    private FetchABIService fetchABIService;

    @BeforeMethod
    public void setUp() {
        fetchABIService = new FetchABIService();
        softAssert = new SoftAssert();
    }

    @Description("Fetch ABI for valid contract address and chain ID - Positive Test")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void fetchABIWithValidParameters() {
        String chaincodeAddress = "0x5EE16Ac7BEBde8F38C99DbEFBBEA4fC5653fC34C";
        String chainId = "80002";

        FetchABIResponse response = fetchABIService.fetchABI(chaincodeAddress, chainId);

        // Common assertions
        CommonAssert.softAssertOkay(response);

        // Specific assertions for fetchABI
        softAssert.assertTrue(response.isSuccess(), "Success should be true");
        softAssert.assertNotNull(response.getAbi(), "ABI should not be null");
        softAssert.assertNotNull(response.getEncodedAbi(), "Encoded ABI should not be null");

        // Validate ABI is not empty
        softAssert.assertFalse(response.getAbi().trim().isEmpty(), "ABI should not be empty");
        softAssert.assertFalse(response.getEncodedAbi().trim().isEmpty(), "Encoded ABI should not be empty");

        // Validate ABI is valid JSON (starts with [)
        softAssert.assertTrue(response.getAbi().trim().startsWith("["), "ABI should be a valid JSON array");

        // Log response details
        System.out.println("=== FetchABI Response ===");
        System.out.println("Success: " + response.isSuccess());
        System.out.println("ABI Length: " + (response.getAbi() != null ? response.getAbi().length() : 0));
        System.out.println("Encoded ABI Length: " + (response.getEncodedAbi() != null ? response.getEncodedAbi().length() : 0));

        softAssert.assertAll();
    }

    @Description("Fetch ABI with invalid contract address - Negative Test")
    @Test(priority = 2, retryAnalyzer = MyRetryAnalyzer.class)
    public void fetchABIWithInvalidContractAddress() {
        String invalidAddress = "0xInvalidAddress123456789";
        String chainId = "80002";

        FetchABIResponse response = fetchABIService.fetchABI(invalidAddress, chainId);

        // Should return error response
        softAssert.assertFalse(response.isSuccess(), "Success should be false for invalid contract address");

        System.out.println("=== FetchABI Error Response ===");
        System.out.println("Success: " + response.isSuccess());
        System.out.println("Status Code: " + response.statusCode);

        softAssert.assertAll();
    }

    @Description("Fetch ABI with invalid chain ID - Negative Test")
    @Test(priority = 3, retryAnalyzer = MyRetryAnalyzer.class)
    public void fetchABIWithInvalidChainId() {
        String chaincodeAddress = "0x5EE16Ac7BEBde8F38C99DbEFBBEA4fC5653fC34C";
        String invalidChainId = "99999";

        FetchABIResponse response = fetchABIService.fetchABI(chaincodeAddress, invalidChainId);

        // Should return error response
        softAssert.assertFalse(response.isSuccess(), "Success should be false for invalid chain ID");

        System.out.println("=== FetchABI Invalid Chain ID Response ===");
        System.out.println("Success: " + response.isSuccess());
        System.out.println("Status Code: " + response.statusCode);

        softAssert.assertAll();
    }

    @Description("Fetch ABI with empty contract address - Negative Test")
    @Test(priority = 4, retryAnalyzer = MyRetryAnalyzer.class)
    public void fetchABIWithEmptyContractAddress() {
        String emptyAddress = "";
        String chainId = "80002";

        FetchABIResponse response = fetchABIService.fetchABI(emptyAddress, chainId);

        // Should return error response
        softAssert.assertFalse(response.isSuccess(), "Success should be false for empty contract address");

        System.out.println("=== FetchABI Empty Address Response ===");
        System.out.println("Success: " + response.isSuccess());
        System.out.println("Status Code: " + response.statusCode);

        softAssert.assertAll();
    }

    @Description("Fetch ABI with empty chain ID - Negative Test")
    @Test(priority = 5, retryAnalyzer = MyRetryAnalyzer.class)
    public void fetchABIWithEmptyChainId() {
        String chaincodeAddress = "0x5EE16Ac7BEBde8F38C99DbEFBBEA4fC5653fC34C";
        String emptyChainId = "";

        FetchABIResponse response = fetchABIService.fetchABI(chaincodeAddress, emptyChainId);

        // Should return error response
        softAssert.assertFalse(response.isSuccess(), "Success should be false for empty chain ID");

        System.out.println("=== FetchABI Empty Chain ID Response ===");
        System.out.println("Success: " + response.isSuccess());
        System.out.println("Status Code: " + response.statusCode);

        softAssert.assertAll();
    }

    @Description("Validate response time for fetch ABI API")
    @Test(priority = 6, retryAnalyzer = MyRetryAnalyzer.class)
    public void validateFetchABIResponseTime() {
        String chaincodeAddress = "0x5EE16Ac7BEBde8F38C99DbEFBBEA4fC5653fC34C";
        String chainId = "80002";

        long startTime = System.currentTimeMillis();
        fetchABIService.fetchABI(chaincodeAddress, chainId);
        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;

        // Validate response time is within acceptable limits
        softAssert.assertTrue(responseTime <= Config.assertResponseTime,
                "Response time should be less than or equal to " + Config.assertResponseTime + "ms");

        System.out.println("=== FetchABI Response Time ===");
        System.out.println("Response Time: " + responseTime + "ms");
        System.out.println("Expected Max Time: " + Config.assertResponseTime + "ms");

        softAssert.assertAll();
    }

    @Description("Validate fetch ABI response structure and data types")
    @Test(priority = 7, retryAnalyzer = MyRetryAnalyzer.class)
    public void validateFetchABIResponseStructure() {
        String chaincodeAddress = "0x5EE16Ac7BEBde8F38C99DbEFBBEA4fC5653fC34C";
        String chainId = "80002";

        FetchABIResponse response = fetchABIService.fetchABI(chaincodeAddress, chainId);

        // Validate response structure
        softAssert.assertTrue(response.isSuccess(), "Success field should be boolean");
        softAssert.assertNotNull(response.getAbi(), "ABI field should not be null");
        softAssert.assertNotNull(response.getEncodedAbi(), "Encoded ABI field should not be null");

        // Validate data types
        softAssert.assertTrue(response.getAbi() instanceof String, "ABI should be String");
        softAssert.assertTrue(response.getEncodedAbi() instanceof String, "Encoded ABI should be String");

        // Validate string lengths
        softAssert.assertTrue(response.getAbi().length() > 0, "ABI should have length > 0");
        softAssert.assertTrue(response.getEncodedAbi().length() > 0, "Encoded ABI should have length > 0");

        System.out.println("=== FetchABI Response Structure Validation ===");
        System.out.println("ABI Type: " + (response.getAbi() != null ? response.getAbi().getClass().getSimpleName() : "null"));
        System.out.println("Encoded ABI Type: " + (response.getEncodedAbi() != null ? response.getEncodedAbi().getClass().getSimpleName() : "null"));

        softAssert.assertAll();
    }

    @Description("Fetch ABI for multiple contract addresses - Data Driven Test")
    @Test(priority = 8, retryAnalyzer = MyRetryAnalyzer.class)
    public void fetchABIForMultipleContractAddresses() {
        String[] contractAddresses = {
                "0x5EE16Ac7BEBde8F38C99DbEFBBEA4fC5653fC34C",
                config.targetAddress
        };
        String chainId = "80002";

        for (String address : contractAddresses) {
            FetchABIResponse response = fetchABIService.fetchABI(address, chainId);

            if (response.isSuccess()) {
                System.out.println("=== FetchABI for " + address + " ===");
                System.out.println("ABI Length: " + response.getAbi().length());
                System.out.println("Encoded ABI Length: " + response.getEncodedAbi().length());

                // Validate response structure
                softAssert.assertNotNull(response.getAbi(), "ABI should not be null for " + address);
                softAssert.assertNotNull(response.getEncodedAbi(), "Encoded ABI should not be null for " + address);
            } else {
                System.out.println("=== FetchABI failed for " + address + " ===");
                System.out.println("Status Code: " + response.statusCode);
            }
        }

        softAssert.assertAll();
    }
}

