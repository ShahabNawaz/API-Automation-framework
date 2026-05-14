package in.mai.yourproject.api.webhooks;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;

import static in.mai.yourproject.api.common.MyUtil.getRandomHexString;
import static io.restassured.RestAssured.given;

public class WebhooksService {

    private final Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public WebhooksResponse createWebhook(ITestContext context) {
        String url = config.kwala_url+ ApiEndPoints.TRACKER_EVENT;

        WebhooksRequest request = new WebhooksRequest(
                config.getWebhookName() + getRandomHexString(6),
                config.getWebhookContractAddress(),
                config.getEventFilters(),
                config.getEventsSignature(),
                config.getEventsReceiveUrl(),
                config.getWorkflowId(),
                "repeat_every",
                config.getAbiEncoded(),
                config.getWebhookSecret(),
                String.valueOf(config.getChainId())
        );

        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", config.getWebhookSecret())
                .body(request)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(request), response);

        WebhooksResponse webhooksResponse = parseTrackerEventResponse(response);
        ApiUtil.updateResponse(response, webhooksResponse);
        return webhooksResponse;
    }

    private WebhooksResponse parseTrackerEventResponse(Response response) {
        String body = response.asString();

        WebhooksResponse parsed = new WebhooksResponse();

        if (body == null || body.trim().isEmpty()) {
            parsed.setSuccess(ApiUtil.isSuccessfulResponse(response));
            return parsed;
        }

        try {
            JsonElement el = JsonParser.parseString(body);
            if (el != null && el.isJsonObject()) {
                JsonObject obj = el.getAsJsonObject();
                return new Gson().fromJson(obj, WebhooksResponse.class);
            }

            if (el != null && el.isJsonPrimitive() && el.getAsJsonPrimitive().isString()) {
                String subscriptionId = el.getAsString();
                parsed.setSuccess(ApiUtil.isSuccessfulResponse(response));
                parsed.setSubscriptionId(subscriptionId);
                return parsed;
            }
        } catch (Exception ignored) {
            // fallthrough to plain-text handling
        }

        parsed.setSuccess(ApiUtil.isSuccessfulResponse(response));
        parsed.setSubscriptionId(body.replace("\"", "").trim());
        return parsed;
    }
}

