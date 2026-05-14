package in.mai.yourproject.api.getsupportedchains;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

import java.util.Map;

public class GetSupportedChainsResponse extends BaseResponse {

    @SerializedName("success")
    private boolean success;

    // Keys are chain IDs (e.g., "1", "10", "56"), values are chain info objects
    @SerializedName("data")
    private Map<String, ChainInfo> data;

    public boolean isSuccess() {
        return success;
    }

    public Map<String, ChainInfo> getData() {
        return data;
    }
}

