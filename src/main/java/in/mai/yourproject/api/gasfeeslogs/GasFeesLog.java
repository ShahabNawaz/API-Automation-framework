package in.mai.yourproject.api.gasfeeslogs;

import com.google.gson.annotations.SerializedName;

public class GasFeesLog {

	@SerializedName("from")
	private String from;

	@SerializedName("gas")
	private String gas;

	@SerializedName("txID")
	private String txID;

	@SerializedName("workflow_id")
	private String workflowId;

	@SerializedName("action_count")
	private int actionCount;

	@SerializedName("chain_id")
	private int chainId;

	@SerializedName("status")
	private boolean status;

	public String getFrom(){
		return from;
	}

	public String getGas(){
		return gas;
	}

	public String getTxID(){
		return txID;
	}

	public String getWorkflowId(){
		return workflowId;
	}

	public int getActionCount(){
		return actionCount;
	}

	public int getChainId(){
		return chainId;
	}

	public boolean isStatus(){
		return status;
	}
}

