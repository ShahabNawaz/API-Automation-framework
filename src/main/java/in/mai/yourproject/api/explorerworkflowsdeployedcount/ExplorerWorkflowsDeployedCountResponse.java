package in.mai.yourproject.api.explorerworkflowsdeployedcount;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class ExplorerWorkflowsDeployedCountResponse extends BaseResponse {

	@SerializedName("count")
	public Long count;

	public Long getCount(){
		return count;
	}
}

