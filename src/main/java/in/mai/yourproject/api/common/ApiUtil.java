package in.mai.yourproject.api.common;

import com.google.gson.Gson;
import io.qameta.allure.Allure;
import io.restassured.response.Response;

public class ApiUtil {

    public static boolean isSuccessfulResponse(Response response) {
        return (response.getStatusCode() >= 200 && response.getStatusCode() < 300);
    }

    public static void updateResponse(Response response, BaseResponse baseresponse) {
        baseresponse.statusCode = response.getStatusCode();
        baseresponse.ok = ApiUtil.isSuccessfulResponse(response);
    }

    /**
     * Attaches request and response details to Allure report
     * @param url The request URL
     * @param method The HTTP method (GET, POST, etc.)
     * @param response The RestAssured response object
     */
    public static void attachRequestResponseToAllure(String url, String method, Response response) {
        attachRequestResponseToAllure(url, method, null, response);
    }

    /**
     * Converts an object to JSON string for logging
     * @param obj The object to convert
     * @return JSON string representation
     */
    public static String objectToJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            Gson gson = new Gson();
            return gson.toJson(obj);
        } catch (Exception e) {
            return obj.toString();
        }
    }

    /**
     * Attaches request and response details to Allure report
     * @param url The request URL
     * @param method The HTTP method (GET, POST, etc.)
     * @param requestBody The request body (can be null for GET requests)
     * @param response The RestAssured response object
     */
    public static void attachRequestResponseToAllure(String url, String method, String requestBody, Response response) {
        // Attach request details
        StringBuilder requestDetails = new StringBuilder();
        requestDetails.append("Request URL: ").append(url).append("\n");
        requestDetails.append("Request Method: ").append(method != null ? method : "GET").append("\n");
        
        // Add request body if available
        if (requestBody != null && !requestBody.isEmpty()) {
            requestDetails.append("Request Body:\n").append(requestBody).append("\n");
        }

        Allure.addAttachment("Request Details", "text/plain", requestDetails.toString());
        
        // Attach request body as JSON if it's JSON
        if (requestBody != null && !requestBody.isEmpty()) {
            try {
                Allure.addAttachment("Request Body (JSON)", "application/json", requestBody);
            } catch (Exception e) {
                // If not valid JSON, attach as text
                Allure.addAttachment("Request Body", "text/plain", requestBody);
            }
        }

        // Attach response details
        StringBuilder responseDetails = new StringBuilder();
        responseDetails.append("Response Status Code: ").append(response.getStatusCode()).append("\n");
        responseDetails.append("Response Status Line: ").append(response.getStatusLine()).append("\n");
        responseDetails.append("Response Headers:\n").append(response.getHeaders().toString()).append("\n");
        responseDetails.append("Response Time: ").append(response.getTime()).append(" ms\n");
        responseDetails.append("\nResponse Body:\n").append(response.asPrettyString());

        Allure.addAttachment("Response Details", "text/plain", responseDetails.toString());
        
        // Also attach response as JSON for better formatting
        Allure.addAttachment("Response Body (JSON)", "application/json", response.asString());
    }
}
