package in.mai.yourproject.api.workflowstatus;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class WorkflowStatusResponse extends BaseResponse {

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }
}


