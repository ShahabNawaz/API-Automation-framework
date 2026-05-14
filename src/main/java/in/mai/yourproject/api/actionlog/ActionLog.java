package in.mai.yourproject.api.actionlog;

import com.google.gson.annotations.SerializedName;

public class ActionLog {

    @SerializedName("id")
    private int id;

    @SerializedName("workflow_id")
    private String workflowId;

    @SerializedName("action_id")
    private String actionId;

    @SerializedName("execution_time")
    private double executionTime;

    @SerializedName("chain_id")
    private int chainId;

    @SerializedName("success")
    private boolean success;

    @SerializedName("workflow_starttime")
    private long workflowStarttime;

    @SerializedName("retries")
    private int retries;

    @SerializedName("next_run")
    private long nextRun;

    @SerializedName("gas_fees")
    private String gasFees;

    @SerializedName("error")
    private String error;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("deleted_at")
    private String deletedAt;

    public int getId() {
        return id;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public String getActionId() {
        return actionId;
    }

    public double getExecutionTime() {
        return executionTime;
    }

    public int getChainId() {
        return chainId;
    }

    public boolean isSuccess() {
        return success;
    }

    public long getWorkflowStarttime() {
        return workflowStarttime;
    }

    public int getRetries() {
        return retries;
    }

    public long getNextRun() {
        return nextRun;
    }

    public String getGasFees() {
        return gasFees;
    }

    public String getError() {
        return error;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }
}

