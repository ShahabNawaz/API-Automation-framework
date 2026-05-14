package in.mai.yourproject.api.transactionsestimate;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class TransactionEstimateResponse extends BaseResponse {

	@SerializedName("transactionFee")
	private String transactionFee;

	@SerializedName("success")
	private boolean success;

	@SerializedName("currency")
	private String currency;

	public String getTransactionFee(){
		return transactionFee;
	}

	public boolean isSuccess(){
		return success;
	}

	public String getCurrency(){
		return currency;
	}
}

