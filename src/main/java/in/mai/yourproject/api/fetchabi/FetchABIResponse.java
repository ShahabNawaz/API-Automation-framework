package in.mai.yourproject.api.fetchabi;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class FetchABIResponse extends BaseResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("abi")
    private String abi;

    @SerializedName("encoded_abi")
    private String encodedAbi;

    public boolean isSuccess() {
        return success;
    }

    public String getAbi() {
        return abi;
    }

    public String getEncodedAbi() {
        return encodedAbi;
    }
}

