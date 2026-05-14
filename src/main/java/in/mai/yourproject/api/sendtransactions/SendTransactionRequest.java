package in.mai.yourproject.api.sendtransactions;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SendTransactionRequest{

	@SerializedName("args")
	private List<String> args;

	@SerializedName("chainId")
	private int chainId;

	@SerializedName("functionSignature")
	private String functionSignature;

	@SerializedName("contractAddress")
	private String contractAddress;

	@SerializedName("rpcUrl")
	private String rpcUrl;

	public List<String> getArgs(){
		return args;
	}

	public int getChainId(){
		return chainId;
	}

	public String getFunctionSignature(){
		return functionSignature;
	}

	public String getContractAddress(){
		return contractAddress;
	}

	public String getRpcUrl(){
		return rpcUrl;
	}

	public SendTransactionRequest(List<String> args, int chainId, String functionSignature, String contractAddress, String rpcUrl) {
		this.args = args;
		this.chainId = chainId;
		this.functionSignature = functionSignature;
		this.contractAddress = contractAddress;
		this.rpcUrl = rpcUrl;
	}
}