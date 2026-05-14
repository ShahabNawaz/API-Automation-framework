package in.mai.yourproject.api.fetchtokenswithchaininfo;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ChainData {

	@SerializedName("mainnetChains")
	private Map<String, ChainInfo> mainnetChains;

	@SerializedName("testnetChains")
	private Map<String, ChainInfo> testnetChains;

	public Map<String, ChainInfo> getMainnetChains(){
		return mainnetChains;
	}

	public Map<String, ChainInfo> getTestnetChains(){
		return testnetChains;
	}
}

