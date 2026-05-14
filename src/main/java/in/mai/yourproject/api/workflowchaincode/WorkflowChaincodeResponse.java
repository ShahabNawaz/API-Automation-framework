package in.mai.yourproject.api.workflowchaincode;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class WorkflowChaincodeResponse extends BaseResponse {

    @SerializedName("chaincode_address")
    private String chaincodeAddress;

    public String getChaincodeAddress() {
        return chaincodeAddress;
    }
}

