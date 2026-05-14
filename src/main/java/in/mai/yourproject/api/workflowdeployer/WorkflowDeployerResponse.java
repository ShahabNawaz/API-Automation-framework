package in.mai.yourproject.api.workflowdeployer;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

import java.util.List;

public class WorkflowDeployerResponse extends BaseResponse {

	@SerializedName("total_workflows")
	private int totalWorkflows;

	@SerializedName("workflows")
	private List<WorkflowInfo> workflows;

	public int getTotalWorkflows(){
		return totalWorkflows;
	}

	public List<WorkflowInfo> getWorkflows(){
		return workflows;
	}
}

