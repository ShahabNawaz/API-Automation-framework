package in.mai.yourproject.api.deploycontract;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class DeployContractResponse extends BaseResponse {

	@SerializedName("success")
	private boolean success;

	@SerializedName("contractAddress")
	private String contractAddress;

	@SerializedName("transactionHash")
	private String transactionHash;

	public boolean isSuccess(){
		return success;
	}

	public String getContractAddress(){
		return contractAddress;
	}

	public String getTransactionHash(){
		return transactionHash;
	}
}