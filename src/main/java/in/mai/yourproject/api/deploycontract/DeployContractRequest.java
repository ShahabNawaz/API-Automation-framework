package in.mai.yourproject.api.deploycontract;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DeployContractRequest{

	@SerializedName("bytecode")
	private String bytecode;

	@SerializedName("tokenStandard")
	private String tokenStandard;

	@SerializedName("chainId")
	private int chainId;

	@SerializedName("constructorArgs")
	private List<String> constructorArgs;

	@SerializedName("rpcUrl")
	private String rpcUrl;

	@SerializedName("abiEncoded")
	private String abiEncoded;

	public String getBytecode(){
		return bytecode;
	}

	public String getTokenStandard(){
		return tokenStandard;
	}

	public int getChainId(){
		return chainId;
	}

	public List<String> getConstructorArgs(){
		return constructorArgs;
	}

	public String getRpcUrl(){
		return rpcUrl;
	}

	public String getAbiEncoded(){
		return abiEncoded;
	}

	public DeployContractRequest(String bytecode, String tokenStandard, int chainId, List<String> constructorArgs, String rpcUrl, String abiEncoded) {
		this.bytecode = bytecode;
		this.tokenStandard = tokenStandard;
		this.chainId = chainId;
		this.constructorArgs = constructorArgs;
		this.rpcUrl = rpcUrl;
		this.abiEncoded = abiEncoded;
	}
}