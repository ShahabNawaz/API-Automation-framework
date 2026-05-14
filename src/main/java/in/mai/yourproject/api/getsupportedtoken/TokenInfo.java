package in.mai.yourproject.api.getsupportedtoken;

import com.google.gson.annotations.SerializedName;

public class TokenInfo {

    @SerializedName("chainlinkOracleAddress")
    private String chainlinkOracleAddress;

    @SerializedName("chainlinkOracleChainId")
    private int chainlinkOracleChainId;

    // Sample JSON shows slippage as a string
    @SerializedName("slippage")
    private String slippage;

    @SerializedName("tokenName")
    private String tokenName;

    @SerializedName("tokenContractName")
    private String tokenContractName;

    @SerializedName("tokenContractSymbol")
    private String tokenContractSymbol;

    @SerializedName("tokenContractAddress")
    private String tokenContractAddress;

    @SerializedName("tokenContractChainId")
    private int tokenContractChainId;

    @SerializedName("kwalaTokenId")
    private int kwalaTokenId;

    public String getChainlinkOracleAddress() {
        return chainlinkOracleAddress;
    }

    public int getChainlinkOracleChainId() {
        return chainlinkOracleChainId;
    }

    public String getSlippage() {
        return slippage;
    }

    public String getTokenName() {
        return tokenName;
    }

    public String getTokenContractName() {
        return tokenContractName;
    }

    public String getTokenContractSymbol() {
        return tokenContractSymbol;
    }

    public String getTokenContractAddress() {
        return tokenContractAddress;
    }

    public int getTokenContractChainId() {
        return tokenContractChainId;
    }

    public int getKwalaTokenId() {
        return kwalaTokenId;
    }
}

