package in.mai.yourproject.api.fetchtokenswithchaininfo;

import com.google.gson.annotations.SerializedName;

public class ChainInfo {

	@SerializedName("chainName")
	private String chainName;

	@SerializedName("currency")
	private String currency;

	public String getChainName(){
		return chainName;
	}

	public String getCurrency(){
		return currency;
	}
}

