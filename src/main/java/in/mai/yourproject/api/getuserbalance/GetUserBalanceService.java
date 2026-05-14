package in.mai.yourproject.api.getuserbalance;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;

public class GetUserBalanceService {


    Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public GetUserBalanceResponse getUserBalanceResponse(ITestContext context) {
        String url = config.kwala_frontend_url + ApiEndPoints.GET_BALANCE + "kwl-" + config.getEnrollmentId() + "-cc";

        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .get(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "GET", response);

        GetUserBalanceResponse getUserBalanceResponse = response.as(GetUserBalanceResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, getUserBalanceResponse);
        return getUserBalanceResponse;
    }

}
