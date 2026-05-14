package in.mai.yourproject.api.webhooks;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class WebhooksResponse extends BaseResponse {

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

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}

