package in.mai.yourproject.api.fetchtokenswithchaininfo;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class FetchTokensWithChainInfoResponse extends BaseResponse {

	@SerializedName("success")
	private boolean success;

	@SerializedName("data")
	private ChainData data;

	public boolean isSuccess(){
		return success;
	}

	public ChainData getData(){
		return data;
	}
}

