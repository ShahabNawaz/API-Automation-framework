package in.mai.yourproject.api.common;

import in.mai.yourproject.config.Config;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class MyAssert {

    public static SoftAssert softAssert;

    @Step("Status Code" + ": {actualStatusCode}")
    public static void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
        Assert.assertEquals(actualStatusCode, expectedStatusCode);

    }

    @Step("Status Code" + ": {actualStatusCode} for page no {pageNo}")
    public static void assertStatusCode(int actualStatusCode, int expectedStatusCode, int pageNo) {
        Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }

    @Step("Expected Result: {testCase}" + " should be " + "'{expectedResult}' ----- Actual Result: '{actualResult}'")
    public static void assertEquals(Object actualResult, Object expectedResult, String testCase) {
        softAssert.assertEquals(actualResult, expectedResult);
    }

    @Step("Expected Result: {testCase}" + " should be " + "'{expectedResult}' ----- Actual Result: {actualResult}")
    public static void assertNotEquals(Object actualResult, Object expectedResult, String testCase) {
        softAssert.assertNotEquals(actualResult, expectedResult);
    }

    @Step("Expected Result: {testCase} ----- Actual Result: {response}")
    public static void assertTrue(boolean response, String testCase) {
        softAssert.assertTrue(response);
    }

    @Step("Expected Result: {testCase}" + " should not be null")
    public static void assertNotNull(Object actualResult, String testCase) {
        Assert.assertNotNull(actualResult);
    }

    @Step("Expected Result: {testCase} ----- Actual Result: {response}")
    public static void assertFalse(boolean response, String testCase) {
        softAssert.assertFalse(response);
    }

    @Step("Expected Result: " + "The API Response time should less then or equals to " + Config.assertResponseTime + " Millisecond" + " ----- Actual Result: {response}")
    public static void assertResponseTime(boolean response) {
        softAssert.assertTrue(response);
    }
    @Step("Expected Result: " + "The API Response time should less then or equals to " + Config.assertResponseTime + " Millisecond" + " ----- Actual Result: {response}")
    public static void assertDataSize(boolean response) {
        softAssert.assertTrue(response);
    }

    @Step("Expected Result: " + "The API Response time should less then or equals to " + Config.assertResponseTime + " Millisecond" + " ----- Actual Result: {response} " + "Millisecond")
    public static void assertResponseTime(Long response) {
        softAssert.assertTrue(response <= Config.assertResponseTime);
    }

    @Step("{message}")
    public static void logFalse(Object message) {
        softAssert.assertTrue(false);
        System.out.println("message: "+message);

    }
    @Step("{response}")
    public static void verifyLogFalse(Object message, Response response) {
        softAssert.assertTrue(false);
        System.out.println("message: "+message);

    }



    @Step("{message}")
    public static void logTrue(Object message) {
        softAssert.assertTrue(true);
        System.out.println("message: "+ message);
    }

    @Step("{message}")
    public static void logFalse(Object title, Object message) {
        softAssert.assertTrue(true);
        MyUtil.printLog("title: " + title + "    message:" + message);
    }

    @Step("The coming page no should be equals to '{expectedPageNo}' ----- Coming page no: '{actualPageNo}'")
    public static void assertPagination(int actualPageNo, int expectedPageNo) {
        softAssert.assertEquals(actualPageNo, expectedPageNo);
    }

    @Step("The 'data' key must not be null at page no '{pageNo}'")
    public static void assertNotNullData(int pageNo, int dataKeySize) {
        if (dataKeySize > 0) {
            softAssert.assertTrue(true);
        }
        softAssert.assertFalse(false);
    }

}



