package in.mai.yourproject.api.common;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class ServiceLogUtil {


    public static Object serviceLog(Class genericClass , Response response, String url) {
        return serviceLog(genericClass, response, url, "GET", null);
    }

    public static Object serviceLog(Class genericClass , Response response, String url, String method) {
        return serviceLog(genericClass, response, url, method, null);
    }

    public static Object serviceLog(Class genericClass , Response response, String url, String method, String requestBody) {
    Object objResponse = response.as(genericClass, ObjectMapperType.GSON);
    MyUtil.printLog(url);
    MyUtil.printLog(response.asPrettyString());
        
        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, method, requestBody, response);
        
    ApiUtil.updateResponse(response, (BaseResponse) objResponse);

    return objResponse;
}
}
