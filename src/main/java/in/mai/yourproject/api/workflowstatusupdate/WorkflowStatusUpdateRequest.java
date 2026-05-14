package in.mai.yourproject.api.workflowstatusupdate;

import com.google.gson.annotations.SerializedName;

public class WorkflowStatusUpdateRequest {

	@SerializedName("status")
	private String status;

	@SerializedName("workflow_id")
	private String workflowId;

	public String getStatus(){
		return status;
	}

	public String getWorkflowId(){
		return workflowId;
	}

	public WorkflowStatusUpdateRequest(String status, String workflowId) {
		this.status = status;
		this.workflowId = workflowId;
	}
}

