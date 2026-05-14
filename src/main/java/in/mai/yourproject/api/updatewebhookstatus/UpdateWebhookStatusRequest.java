package in.mai.yourproject.api.updatewebhookstatus;

import com.google.gson.annotations.SerializedName;

public class UpdateWebhookStatusRequest {

	@SerializedName("status")
	private String status;

	public String getStatus(){
		return status;
	}

	public UpdateWebhookStatusRequest(String status) {
		this.status = status;
	}
}

