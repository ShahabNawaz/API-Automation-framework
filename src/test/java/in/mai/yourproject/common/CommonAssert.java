package in.mai.yourproject.common;


import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.api.common.BaseResponse;
import in.mai.yourproject.api.common.MyAssert;
import in.mai.yourproject.api.common.MyUtil;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

public class CommonAssert extends MyAssert {


    public static void softAssertOkay(BaseResponse response) {
        softAssert = new SoftAssert();
        if (response.statusCode != 200) {
            MyAssert.logFalse("message: "+response.getErrorMsg());
        }

        MyAssert.assertStatusCode(response.statusCode, 200);
        MyAssert.assertResponseTime(response.time);
    }

    public static void softAssertError(BaseResponse response) {
        softAssert = new SoftAssert();
        if (response.statusCode != 500) {
            MyAssert.logFalse("message: "+response.getErrorMsg());
        }

        MyAssert.assertStatusCode(response.statusCode, 500);
        MyAssert.assertResponseTime(response.time);
    }


    public static void softAssertCreated(BaseResponse response) {
        softAssert = new SoftAssert();
        if (response.statusCode != 201) {
            MyAssert.logFalse( response.getErrorMsg());
            return;
        }

        MyAssert.assertStatusCode(response.statusCode, 201);
        MyAssert.assertResponseTime(response.time);
    }


    public static void softAssertConflict(BaseResponse response) {
        softAssert = new SoftAssert();
        if (response.statusCode != 409) {
            MyAssert.logFalse("message: "+response.getErrorMsg());
        }

        MyAssert.assertStatusCode(response.statusCode, 409);
        MyAssert.assertResponseTime(response.time);
    }


    public static void serviceLog(String url, Response response, BaseResponse baseResponse) {
        MyUtil.printLog(url);
        MyUtil.printLog(response.asPrettyString());
        ApiUtil.updateResponse(response, baseResponse);
    }

    public static void softAssertBadRequest(BaseResponse response) {
        softAssert = new SoftAssert();
        if (response.statusCode != 400) {
            MyAssert.logFalse("message: " + response.getErrorMsg());
        }

        MyAssert.assertStatusCode(response.statusCode, 400);
        MyAssert.assertResponseTime(response.time);
    }

}
