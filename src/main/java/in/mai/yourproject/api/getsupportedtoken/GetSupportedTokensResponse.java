package in.mai.yourproject.api.getsupportedtoken;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;
import java.util.Map;

public class GetSupportedTokensResponse extends BaseResponse {

    @SerializedName("success")
    private boolean success;

    // Keys are dynamic (e.g., "0x..._1"), values are token info objects
    @SerializedName("data")
    private Map<String, TokenInfo> data;

    public boolean isSuccess() {
        return success;
    }

    public Map<String, TokenInfo> getData() {
        return data;
    }
}