package in.mai.yourproject.api.getsupportedchains;

import com.google.gson.annotations.SerializedName;

public class ChainInfo {

    @SerializedName("chain_name")
    private String chainName;

    @SerializedName("gas_token_primary_key")
    private String gasTokenPrimaryKey;

    @SerializedName("currency")
    private String currency;

    @SerializedName("chain_id")
    private int chainId;

    @SerializedName("created_at")
    private String createdAt;

    public String getChainName() {
        return chainName;
    }

    public String getGasTokenPrimaryKey() {
        return gasTokenPrimaryKey;
    }

    public String getCurrency() {
        return currency;
    }

    public int getChainId() {
        return chainId;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}

