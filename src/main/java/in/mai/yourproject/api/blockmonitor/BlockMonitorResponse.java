package in.mai.yourproject.api.blockmonitor;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class BlockMonitorResponse extends BaseResponse {

	@SerializedName("success")
	private boolean success;

	public boolean isSuccess(){
		return success;
	}
}

