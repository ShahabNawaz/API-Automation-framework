package in.mai.yourproject.api.blockmonitor;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;

public class BlockMonitorService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public BlockMonitorResponse monitorBlock(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.BLOCK_MONITOR;

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .queryParam("targetBlockNumber", config.getTargetBlockNumber())
                .queryParam("chainId", String.valueOf(config.getChainId()))
                .get(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(
                url,
                "GET",
                String.format("targetBlockNumber=%s&chainId=%s",
                        config.getTargetBlockNumber(),
                        String.valueOf(config.getChainId())),
                response
        );

        BlockMonitorResponse monitorResponse = response.as(BlockMonitorResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, monitorResponse);
        return monitorResponse;
    }
}
