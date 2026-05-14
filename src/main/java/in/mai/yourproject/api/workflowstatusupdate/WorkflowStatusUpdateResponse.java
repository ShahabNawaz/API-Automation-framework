package in.mai.yourproject.api.workflowstatusupdate;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class WorkflowStatusUpdateResponse extends BaseResponse {

	@SerializedName("success")
	private boolean success;

	public boolean isSuccess(){
		return success;
	}
}

