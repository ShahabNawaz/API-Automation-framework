package in.mai.yourproject.api.workflowdetail;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class WorkflowDetailResponse extends BaseResponse {

	@SerializedName("workflow_name")
	private String workflowName;

	@SerializedName("workflow_id")
	private String workflowId;

	@SerializedName("repeatEvery")
	private String repeatEvery;

	@SerializedName("expires_in")
	private long expiresIn;

	@SerializedName("execute_after")
	private String executeAfter;

	@SerializedName("deployed")
	private boolean deployed;

	@SerializedName("yaml")
	private String yaml;

	@SerializedName("status")
	private String status;

	@SerializedName("last_run")
	private String lastRun;

	@SerializedName("next_run")
	private String nextRun;

	@SerializedName("last_saved")
	private long lastSaved;

	public String getWorkflowName(){
		return workflowName;
	}

	public String getWorkflowId(){
		return workflowId;
	}

	public String getRepeatEvery(){
		return repeatEvery;
	}

	public long getExpiresIn(){
		return expiresIn;
	}

	public String getExecuteAfter(){
		return executeAfter;
	}

	public boolean isDeployed(){
		return deployed;
	}

	public String getYaml(){
		return yaml;
	}

	public String getStatus(){
		return status;
	}

	public String getLastRun(){
		return lastRun;
	}

	public String getNextRun(){
		return nextRun;
	}

	public long getLastSaved(){
		return lastSaved;
	}
}

