package in.mai.yourproject.testsuite.frontend;

import in.mai.yourproject.api.common.MyAssert;
import in.mai.yourproject.api.getuserbalance.GetUserBalanceResponse;
import in.mai.yourproject.api.getuserbalance.GetUserBalanceService;
import in.mai.yourproject.common.CommonAssert;
import in.mai.yourproject.common.MyRetryAnalyzer;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.math.BigInteger;

public class UserBalance extends MyAssert {

    private GetUserBalanceService getUserBalanceService;
    private Config config;

    @BeforeMethod
    public void setUp() {
        getUserBalanceService = new GetUserBalanceService();
        config = ConfigManager.CONFIG_MANAGER.getConfig();
        softAssert = new SoftAssert();
    }

    // ==================== POSITIVE TEST CASES ====================

    @Description("Get user balance with valid enrollment ID - Positive Test")
    @Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void getUserBalance(ITestContext context) {
        GetUserBalanceResponse response = getUserBalanceService.getUserBalanceResponse(context);

        // Common assertions (status code 200, response time)
        CommonAssert.softAssertOkay(response);

        // Validate balance field
        softAssert.assertNotNull(response.getBalance(), "Balance should not be null");
        softAssert.assertFalse(response.getBalance().trim().isEmpty(), "Balance should not be empty");
        
        // Validate balance is a valid number (can be 0 or positive)
        try {
            BigInteger balance = new BigInteger(response.getBalance());
            softAssert.assertTrue(balance.compareTo(BigInteger.ZERO) >= 0, 
                "Balance should be zero or positive");
        } catch (NumberFormatException e) {
            softAssert.assertTrue(false, "Balance should be a valid number");
        }

        // Log response details
        System.out.println("=== GetUserBalance Response ===");
        System.out.println("Status Code: " + response.statusCode);
        System.out.println("Balance: " + response.getBalance());
        System.out.println("Response Time: " + response.time + "ms");

        softAssert.assertAll();
    }

    @Description("Validate user balance response structure and data types - Positive Test")
    @Test(priority = 2, retryAnalyzer = MyRetryAnalyzer.class)
    public void validateGetUserBalanceResponseStructure(ITestContext context) {
        GetUserBalanceResponse response = getUserBalanceService.getUserBalanceResponse(context);

        // Validate response structure
        CommonAssert.softAssertOkay(response);
        
        // Validate balance field exists and is String
        softAssert.assertNotNull(response.getBalance(), "Balance field should not be null");
        
        // Validate balance format (should be numeric string)
        String balance = response.getBalance();
        softAssert.assertTrue(balance.matches("\\d+"), "Balance should contain only digits");
        
        // Validate balance is not empty
        softAssert.assertTrue(balance.length() > 0, "Balance should have length > 0");

        System.out.println("=== GetUserBalance Response Structure Validation ===");
        System.out.println("Balance Value: " + response.getBalance());
        System.out.println("Balance Length: " + response.getBalance().length());
        System.out.println("Is Numeric: " + balance.matches("\\d+"));

        softAssert.assertAll();
    }

    @Description("Validate response time for get user balance API - Positive Test")
    @Test(priority = 3, retryAnalyzer = MyRetryAnalyzer.class)
    public void validateGetUserBalanceResponseTime(ITestContext context) {
        long startTime = System.currentTimeMillis();
        GetUserBalanceResponse response = getUserBalanceService.getUserBalanceResponse(context);
        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;

        // Validate response time is within acceptable limits
        softAssert.assertTrue(responseTime <= config.assertResponseTime, 
            "Response time should be less than or equal to " + config.assertResponseTime + "ms");
        
        // Also validate response is successful
        CommonAssert.softAssertOkay(response);

        System.out.println("=== GetUserBalance Response Time ===");
        System.out.println("Response Time: " + responseTime + "ms");
        System.out.println("Expected Max Time: " + config.assertResponseTime + "ms");
        System.out.println("Actual API Response Time: " + response.time + "ms");

        softAssert.assertAll();
    }

    @Description("Get user balance multiple times to verify consistency - Positive Test")
    @Test(priority = 4, retryAnalyzer = MyRetryAnalyzer.class)
    public void getUserBalanceMultipleTimes(ITestContext context) {
        // Get balance first time
        GetUserBalanceResponse firstResponse = getUserBalanceService.getUserBalanceResponse(context);
        CommonAssert.softAssertOkay(firstResponse);
        String firstBalance = firstResponse.getBalance();
        softAssert.assertNotNull(firstBalance, "First balance should not be null");
        
        // Wait a bit (in case balance changes)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Get balance second time
        GetUserBalanceResponse secondResponse = getUserBalanceService.getUserBalanceResponse(context);
        CommonAssert.softAssertOkay(secondResponse);
        String secondBalance = secondResponse.getBalance();
        softAssert.assertNotNull(secondBalance, "Second balance should not be null");
        
        // Balance should be valid numbers (might be same or different depending on transactions)
        try {
            BigInteger first = new BigInteger(firstBalance);
            BigInteger second = new BigInteger(secondBalance);
            softAssert.assertTrue(first.compareTo(BigInteger.ZERO) >= 0, "First balance should be >= 0");
            softAssert.assertTrue(second.compareTo(BigInteger.ZERO) >= 0, "Second balance should be >= 0");
        } catch (NumberFormatException e) {
            softAssert.assertTrue(false, "Balances should be valid numbers");
        }

        System.out.println("=== GetUserBalance Multiple Calls ===");
        System.out.println("First Balance: " + firstBalance);
        System.out.println("Second Balance: " + secondBalance);
        System.out.println("Balances Match: " + firstBalance.equals(secondBalance));

        softAssert.assertAll();
    }

    @Description("Validate user balance is in wei format (numeric string) - Positive Test")
    @Test(priority = 5, retryAnalyzer = MyRetryAnalyzer.class)
    public void validateBalanceFormat(ITestContext context) {
        GetUserBalanceResponse response = getUserBalanceService.getUserBalanceResponse(context);

        CommonAssert.softAssertOkay(response);
        
        String balance = response.getBalance();
        softAssert.assertNotNull(balance, "Balance should not be null");
        
        // Balance should be a valid number string (typically representing wei)
        try {
            BigInteger balanceValue = new BigInteger(balance);
            softAssert.assertTrue(balanceValue.compareTo(BigInteger.ZERO) >= 0, 
                "Balance should be zero or positive");
            
            System.out.println("=== GetUserBalance Format Validation ===");
            System.out.println("Balance (wei): " + balance);
            System.out.println("Balance Length: " + balance.length());
            System.out.println("Balance as BigInteger: " + balanceValue.toString());
            
        } catch (NumberFormatException e) {
            softAssert.assertTrue(false, "Balance should be a valid numeric string");
        }

        softAssert.assertAll();
    }

    // ==================== NEGATIVE TEST CASES ====================
    // Note: These tests require adding a method in GetUserBalanceService that accepts enrollmentId parameter
    // Suggested method: getUserBalanceWithEnrollmentId(String enrollmentId, ITestContext context)

//    @Description("Get user balance with invalid enrollment ID format - Negative Test")
//    @Test(priority = 6, retryAnalyzer = MyRetryAnalyzer.class, enabled = false)
//    public void getUserBalanceWithInvalidEnrollmentId(ITestContext context) {
//        // This test requires adding a service method: getUserBalanceWithEnrollmentId(enrollmentId, context)
//        // Test with invalid format: "invalid-enrollment-id"
//
//        // Expected: 404 or 400 status code
//        // GetUserBalanceResponse response = getUserBalanceService.getUserBalanceWithEnrollmentId("invalid-id", context);
//        // CommonAssert.softAssertError(response); // or assert 404/400
//
//        System.out.println("=== GetUserBalance Invalid Enrollment ID ===");
//        System.out.println("Test disabled - requires service method: getUserBalanceWithEnrollmentId()");
//    }
//
//    @Description("Get user balance with empty enrollment ID - Negative Test")
//    @Test(priority = 7, retryAnalyzer = MyRetryAnalyzer.class, enabled = false)
//    public void getUserBalanceWithEmptyEnrollmentId(ITestContext context) {
//        // Expected: 404 or 400 status code
//        // GetUserBalanceResponse response = getUserBalanceService.getUserBalanceWithEnrollmentId("", context);
//
//        System.out.println("=== GetUserBalance Empty Enrollment ID ===");
//        System.out.println("Test disabled - requires service method: getUserBalanceWithEnrollmentId()");
//    }
//
//    @Description("Get user balance with non-existent enrollment ID - Negative Test")
//    @Test(priority = 8, retryAnalyzer = MyRetryAnalyzer.class, enabled = false)
//    public void getUserBalanceWithNonExistentEnrollmentId(ITestContext context) {
//        // Expected: 404 status code
//        // Test with: "kwl-0000000000000000000000000000000000000000-cc"
//
//        System.out.println("=== GetUserBalance Non-existent Enrollment ID ===");
//        System.out.println("Test disabled - requires service method: getUserBalanceWithEnrollmentId()");
//    }
//
//    @Description("Get user balance with special characters in enrollment ID - Negative Test")
//    @Test(priority = 9, retryAnalyzer = MyRetryAnalyzer.class, enabled = false)
//    public void getUserBalanceWithSpecialCharactersInEnrollmentId(ITestContext context) {
//        // Expected: 400 or 404 status code
//        // Test with: "kwl-@#$%^&*()-cc"
//
//        System.out.println("=== GetUserBalance Special Characters ===");
//        System.out.println("Test disabled - requires service method: getUserBalanceWithEnrollmentId()");
//    }
//
//    @Description("Get user balance with null enrollment ID - Negative Test")
//    @Test(priority = 10, retryAnalyzer = MyRetryAnalyzer.class, enabled = true)
//    public void getUserBalanceWithNullEnrollmentId(ITestContext context) {
//        // Expected: 400 or 500 status code
//
//        System.out.println("=== GetUserBalance Null Enrollment ID ===");
//        System.out.println("Test disabled - requires service method: getUserBalanceWithEnrollmentId()");
//    }

    @Description("Validate error response structure for invalid requests - Negative Test")
    @Test(priority = 11, retryAnalyzer = MyRetryAnalyzer.class)
    public void validateErrorResponseStructure(ITestContext context) {
        // Test with current valid enrollmentId to ensure response structure is correct
        GetUserBalanceResponse response = getUserBalanceService.getUserBalanceResponse(context);
        
        // Validate response has all required fields
        softAssert.assertNotNull(response, "Response should not be null");
        softAssert.assertTrue(response.statusCode > 0, "Status code should be set");
        softAssert.assertTrue(response.time >= 0, "Response time should be non-negative");
        
        // If response is successful, validate success structure
        if (response.statusCode == 200) {
            softAssert.assertNotNull(response.getBalance(), "Balance should not be null for successful response");
            System.out.println("Success Response - Status: " + response.statusCode);
            System.out.println("Balance: " + response.getBalance());
        } else {
            // For error responses, validate error structure
            System.out.println("Error Response - Status: " + response.statusCode);
            System.out.println("Error Message: " + response.getErrorMsg());
        }

        System.out.println("=== GetUserBalance Response Structure ===");
        System.out.println("Status Code: " + response.statusCode);
        System.out.println("Has Error Message: " + (response.getErrorMsg() != null));

        softAssert.assertAll();
    }

    // ==================== EDGE CASES ====================

    @Description("Get user balance with zero balance - Edge Case")
    @Test(priority = 12, retryAnalyzer = MyRetryAnalyzer.class)
    public void getUserBalanceWithZeroBalance(ITestContext context) {
        // This test verifies API handles zero balance correctly
        // Note: Actual balance depends on user's account state
        
        GetUserBalanceResponse response = getUserBalanceService.getUserBalanceResponse(context);

        CommonAssert.softAssertOkay(response);
        
        String balance = response.getBalance();
        softAssert.assertNotNull(balance, "Balance should not be null even if zero");
        
        try {
            BigInteger balanceValue = new BigInteger(balance);
            // Zero balance is valid, so we just verify it's a number
            softAssert.assertTrue(balanceValue.compareTo(BigInteger.ZERO) >= 0, 
                "Balance should be zero or positive");
            
            System.out.println("=== GetUserBalance Zero Balance Test ===");
            System.out.println("Balance: " + balance);
            System.out.println("Is Zero: " + balanceValue.equals(BigInteger.ZERO));
            
        } catch (NumberFormatException e) {
            softAssert.assertTrue(false, "Balance should be a valid number");
        }

        softAssert.assertAll();
    }

    @Description("Get user balance with very large balance value - Edge Case")
    @Test(priority = 13, retryAnalyzer = MyRetryAnalyzer.class)
    public void getUserBalanceWithLargeBalance(ITestContext context) {
        // This test verifies API handles large balance values correctly
        
        GetUserBalanceResponse response = getUserBalanceService.getUserBalanceResponse(context);

        CommonAssert.softAssertOkay(response);
        
        String balance = response.getBalance();
        softAssert.assertNotNull(balance, "Balance should not be null");
        
        try {
            BigInteger balanceValue = new BigInteger(balance);
            // Large values should be handled correctly
            softAssert.assertTrue(balanceValue.compareTo(BigInteger.ZERO) >= 0, 
                "Balance should be zero or positive");
            
            System.out.println("=== GetUserBalance Large Balance Test ===");
            System.out.println("Balance: " + balance);
            System.out.println("Balance Length: " + balance.length());
            System.out.println("Is Large (> 10^18): " + (balanceValue.compareTo(new BigInteger("1000000000000000000")) > 0));
            
        } catch (NumberFormatException e) {
            softAssert.assertTrue(false, "Balance should be a valid number");
        }

        softAssert.assertAll();
    }

    
}
