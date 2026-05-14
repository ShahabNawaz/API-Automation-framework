package in.mai.yourproject.api.oraclepricewebhook;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class OraclePriceWebhookResponse extends BaseResponse {

	@SerializedName("success")
	private boolean success;

	@SerializedName("subscriptionId")
	private String subscriptionId;

	@SerializedName("createdAt")
	private String createdAt;

	public boolean isSuccess(){
		return success;
	}

	public String getSubscriptionId(){
		return subscriptionId;
	}

	public String getCreatedAt(){
		return createdAt;
	}
}

