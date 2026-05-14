package in.mai.yourproject.api.actionlog;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

import java.util.List;

public class ActionLogSummaryResponse extends BaseResponse {

    @SerializedName("average_execution_time_ms")
    private double averageExecutionTimeMs;

    @SerializedName("chains_used")
    private List<String> chainsUsed;

    @SerializedName("failed_count")
    private int failedCount;

    @SerializedName("successful_count")
    private int successfulCount;

    public double getAverageExecutionTimeMs() {
        return averageExecutionTimeMs;
    }

    public List<String> getChainsUsed() {
        return chainsUsed;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public int getSuccessfulCount() {
        return successfulCount;
    }
}

