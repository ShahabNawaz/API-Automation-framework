package in.mai.yourproject.api.getsupportedchains;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetSupportedChainsService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public GetSupportedChainsResponse getSupportedChains() {
        String url = config.kwala_url + ApiEndPoints.GET_SUPPORTED_CHAINS;

        Response response = given()
                .relaxedHTTPSValidation()
                .log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .get(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "GET", response);

        GetSupportedChainsResponse chainsResponse = response.as(GetSupportedChainsResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, chainsResponse);
        return chainsResponse;
    }
}

