package in.mai.yourproject.api.sendtransactions;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class SendTransactionResponse extends BaseResponse {

	@SerializedName("success")
	private boolean success;

	@SerializedName("transaction")
	private String transaction;

	@SerializedName("queue_id")
	private String queueId;

	public boolean isSuccess(){
		return success;
	}

	public String getTransaction(){
		return transaction;
	}

	public String getQueueId(){
		return queueId;
	}
}