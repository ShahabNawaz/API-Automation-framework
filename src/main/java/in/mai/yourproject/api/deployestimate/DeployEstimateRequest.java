package in.mai.yourproject.api.deployestimate;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class DeployEstimateRequest {

	@SerializedName("bytecode")
	private String bytecode;

	@SerializedName("tokenStandard")
	private String tokenStandard;

	@SerializedName("chainId")
	private int chainId;

	/**
	 * Matches the deployEstimate API which expects an
	 * object like:
	 * {
	 *   "constructorArgs": {
	 *     "x": "abc",
	 *     "y": "2"
	 *   }
	 * }
	 */
	@SerializedName("constructorArgs")
	private Map<String, String> constructorArgs;

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

	public Map<String, String> getConstructorArgs(){
		return constructorArgs;
	}

	public String getRpcUrl(){
		return rpcUrl;
	}

	public String getAbiEncoded(){
		return abiEncoded;
	}

	public DeployEstimateRequest(String bytecode,
								 String tokenStandard,
								 int chainId,
								 Map<String, String> constructorArgs,
								 String rpcUrl,
								 String abiEncoded) {
		this.bytecode = bytecode;
		this.tokenStandard = tokenStandard;
		this.chainId = chainId;
		this.constructorArgs = constructorArgs;
		this.rpcUrl = rpcUrl;
		this.abiEncoded = abiEncoded;
	}
}