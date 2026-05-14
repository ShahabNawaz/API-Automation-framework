package in.mai.yourproject.config;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Config {
    public static final long assertResponseTime = 15000;
    @SerializedName("env")
    private String environment;


    private int threadSleepInMillis;

    private int pageLoadTimeoutInMillis;

    public String getEnvironment() {
        return environment;
    }



    public int getThreadSleepInMillis() {
        return threadSleepInMillis;
    }

    public int getPageLoadTimeoutInMillis() {
        return pageLoadTimeoutInMillis;
    }

    @SerializedName("enrollmentId")
    public String enrollmentId;
    public String getEnrollmentId() {
        return enrollmentId;
    }

    @SerializedName("devnet_url")
    public String devnet_url;
    public String getDevnet_url() {
        return devnet_url;
    }

    @SerializedName("kwala_url")

    public String kwala_url;

    public String getKwala_url() {
        return kwala_url;
    }

    @SerializedName("abiEncoded")

    public String abiEncoded;

    public String getAbiEncoded() {
        return abiEncoded;
    }

    @SerializedName("bytecode")

    public String bytecode;

    public String getBytecode() {
        return bytecode;
    }

    @SerializedName("chainId")

    public int chainId;

    public int getChainId() {
        return chainId;
    }
    @SerializedName("functionSignature")

    public String functionSignature;

    public String getFunctionSignature() {
        return functionSignature;
    }

    @SerializedName("targetAddress")

    public String targetAddress;

    public String getTargetAddress() {
        return targetAddress;
    }

    @SerializedName("rpcUrl")

    public String rpcUrl;

    public String getRpcUrl() {
        return rpcUrl;
    }

    @SerializedName("getPriceChainId")

    public int getPriceChainId;

    public int getGetPriceChainId() {
        return getPriceChainId;
    }

    @SerializedName("kwala_frontend_url")

    public String kwala_frontend_url;

    public String getKwala_frontend_url() {
        return kwala_frontend_url;
    }

    @SerializedName("workflow_identifier")
    public String workflowIdentifier;

    public String getWorkflowIdentifier() {
        return workflowIdentifier;
    }

    @SerializedName("targetBlockNumber")
    public long targetBlockNumber;

    public long getTargetBlockNumber() {
        return targetBlockNumber;
    }

    @SerializedName("startTargetPrice")
    public int startTargetPrice;

    public int getStartTargetPrice() {
        return startTargetPrice;
    }

    @SerializedName("endTargetPrice")
    public int endTargetPrice;

    public int getEndTargetPrice() {
        return endTargetPrice;
    }

    @SerializedName("tokensInfo")
    public boolean tokensInfo;

    public boolean isTokensInfo() {
        return tokensInfo;
    }

    @SerializedName("mainnetChains")
    public boolean mainnetChains;

    public boolean isMainnetChains() {
        return mainnetChains;
    }

    @SerializedName("testnetChains")
    public boolean testnetChains;

    public boolean isTestnetChains() {
        return testnetChains;
    }

    @SerializedName("subscriptionId")
    public String subscriptionId;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    @SerializedName("webhookStatus")
    public String webhookStatus;

    public String getWebhookStatus() {
        return webhookStatus;
    }

    @SerializedName("webhookName")
    public String webhookName;

    public String getWebhookName() {
        return webhookName;
    }

    @SerializedName("webhookContractAddress")
    public String webhookContractAddress;

    public String getWebhookContractAddress() {
        return webhookContractAddress;
    }

    @SerializedName("eventFilters")
    public String eventFilters;

    public String getEventFilters() {
        return eventFilters;
    }

    @SerializedName("eventsSignature")
    public List<String> eventsSignature;

    public List<String> getEventsSignature() {
        return eventsSignature;
    }

    @SerializedName("eventsReceiveUrl")
    public String eventsReceiveUrl;

    public String getEventsReceiveUrl() {
        return eventsReceiveUrl;
    }

    @SerializedName("webhookSecret")
    public String webhookSecret;

    public String getWebhookSecret() {
        return webhookSecret;
    }

    @SerializedName("webhookRpcURL")
    public String webhookRpcURL;

    public String getWebhookRpcURL() {
        return webhookRpcURL;
    }

    @SerializedName("oraclePriceWebhookName")
    public String oraclePriceWebhookName;

    public String getOraclePriceWebhookName() {
        return oraclePriceWebhookName;
    }

    @SerializedName("oraclePriceReceiveUrl")
    public String oraclePriceReceiveUrl;

    public String getOraclePriceReceiveUrl() {
        return oraclePriceReceiveUrl;
    }

    @SerializedName("oraclePriceSecret")
    public String oraclePriceSecret;

    public String getOraclePriceSecret() {
        return oraclePriceSecret;
    }

    @SerializedName("oraclePriceContractAddress")
    public String oraclePriceContractAddress;

    public String getOraclePriceContractAddress() {
        return oraclePriceContractAddress;
    }

    @SerializedName("oraclePriceChainId")
    public String oraclePriceChainId;

    public String getOraclePriceChainId() {
        return oraclePriceChainId;
    }

    @SerializedName("oraclePrice")
    public String oraclePrice;

    public String getOraclePrice() {
        return oraclePrice;
    }

    @SerializedName("gasFeesLogsAddress")
    public String gasFeesLogsAddress;

    public String getGasFeesLogsAddress() {
        return gasFeesLogsAddress;
    }

    @SerializedName("workflowStatus")
    public String workflowStatus;

    public String getWorkflowStatus() {
        return workflowStatus;
    }

    @SerializedName("workflowId")
    public String workflowId;

    public String getWorkflowId() {
        return workflowId;
    }

    @SerializedName("deployerAddress")
    public String deployerAddress;

    public String getDeployerAddress() {
        return deployerAddress;
    }

    @SerializedName("page")
    public int page;

    public int getPage() {
        return page;
    }

    @SerializedName("pageSize")
    public int pageSize;

    public int getPageSize() {
        return pageSize;
    }
}
