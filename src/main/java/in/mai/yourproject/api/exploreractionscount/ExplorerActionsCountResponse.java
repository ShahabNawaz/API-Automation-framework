package in.mai.yourproject.api.exploreractionscount;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class ExplorerActionsCountResponse extends BaseResponse {

	@SerializedName("count")
	public Long count;

	public Long getCount(){
		return count;
	}
}

