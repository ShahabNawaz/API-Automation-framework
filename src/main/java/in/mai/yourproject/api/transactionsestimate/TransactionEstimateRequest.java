package in.mai.yourproject.api.transactionsestimate;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionEstimateRequest {

	@SerializedName("functionSignature")
	private String functionSignature;

	@SerializedName("args")
	private List<String> args;

	@SerializedName("rpcUrl")
	private String rpcUrl;

	@SerializedName("contractAddress")
	private String contractAddress;

	@SerializedName("chainId")
	private int chainId;

	public String getFunctionSignature(){
		return functionSignature;
	}

	public List<String> getArgs(){
		return args;
	}

	public String getRpcUrl(){
		return rpcUrl;
	}

	public String getContractAddress(){
		return contractAddress;
	}

	public int getChainId(){
		return chainId;
	}

	public TransactionEstimateRequest(String functionSignature, List<String> args, String rpcUrl, String contractAddress, int chainId) {
		this.functionSignature = functionSignature;
		this.args = args;
		this.rpcUrl = rpcUrl;
		this.contractAddress = contractAddress;
		this.chainId = chainId;
	}
}

