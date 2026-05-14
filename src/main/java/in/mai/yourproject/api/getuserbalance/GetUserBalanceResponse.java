package in.mai.yourproject.api.getuserbalance;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class GetUserBalanceResponse extends BaseResponse {

	@SerializedName("balance")
	private String balance;

	public String getBalance(){
		return balance;
	}
}