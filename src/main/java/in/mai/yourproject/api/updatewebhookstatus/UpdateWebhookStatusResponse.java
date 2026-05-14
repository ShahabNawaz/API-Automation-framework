package in.mai.yourproject.api.updatewebhookstatus;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class UpdateWebhookStatusResponse extends BaseResponse {

	@SerializedName("success")
	private boolean success;

	public boolean isSuccess(){
		return success;
	}

	public String getMessage(){
		return super.getMessage();
	}
}

