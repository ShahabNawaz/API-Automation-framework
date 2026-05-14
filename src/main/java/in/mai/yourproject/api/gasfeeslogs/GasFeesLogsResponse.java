package in.mai.yourproject.api.gasfeeslogs;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

import java.util.List;

public class GasFeesLogsResponse extends BaseResponse {

	@SerializedName("gas_fees_logs")
	private List<GasFeesLog> gasFeesLogs;

	public List<GasFeesLog> getGasFeesLogs(){
		return gasFeesLogs;
	}
}

