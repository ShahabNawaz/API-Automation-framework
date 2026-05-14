package in.mai.yourproject.api.updatewebhookstatus;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.api.common.ITestContextKeys;
import in.mai.yourproject.api.common.ServiceLogUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;

public class UpdateWebhookStatusService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public UpdateWebhookStatusResponse updateWebhookStatus(ITestContext context) {
        String trackerId = resolveTrackerId(context);
        String status = config.getWebhookStatus();
        String url = config.kwala_url + String.format(ApiEndPoints.TRACKER_STATUS, trackerId);

        UpdateWebhookStatusRequest request = new UpdateWebhookStatusRequest(status);

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(request)
                .patch(url);

        return (UpdateWebhookStatusResponse) ServiceLogUtil.serviceLog(
                UpdateWebhookStatusResponse.class,
                response,
                url,
                "PATCH",
                ApiUtil.objectToJson(request)
        );
    }

    private String resolveTrackerId(ITestContext context) {
        Object trackerIdFromContext = context != null ? context.getAttribute(ITestContextKeys.trackerId) : null;
        if (trackerIdFromContext instanceof String && !((String) trackerIdFromContext).isBlank()) {
            return (String) trackerIdFromContext;
        }

        String trackerIdFromSysProp = System.getProperty("trackerId");
        if (trackerIdFromSysProp != null && !trackerIdFromSysProp.isBlank()) {
            return trackerIdFromSysProp;
        }

        return config.getSubscriptionId();
    }
}

