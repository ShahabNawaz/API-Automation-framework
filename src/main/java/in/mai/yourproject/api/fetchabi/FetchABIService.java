package in.mai.yourproject.api.fetchabi;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FetchABIService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public FetchABIResponse fetchABI(String chaincodeAddress, String chainId) {
        String url = config.getKwala_frontend_url() + ApiEndPoints.FETCH_ABI;

        Response response = given()
                .relaxedHTTPSValidation()
                .log().all()
                .contentType(ContentType.JSON)
                .queryParam("chaincode_address", chaincodeAddress)
                .queryParam("chain_id", chainId)
                .get(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "GET", response);

        FetchABIResponse fetchABIResponse = response.as(FetchABIResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, fetchABIResponse);
        return fetchABIResponse;
    }
}

