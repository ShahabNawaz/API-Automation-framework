package in.mai.yourproject.api.actionlog;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

import java.util.List;

public class ActionLogResponse extends BaseResponse {

    @SerializedName("action_logs")
    private List<ActionLog> actionLogs;

    public List<ActionLog> getActionLogs() {
        return actionLogs;
    }
}

