package in.mai.yourproject.api.webhooks;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WebhooksRequest {

	@SerializedName("name")
	private String name;

	@SerializedName("contractAddress")
	private String contractAddress;

	@SerializedName("eventFilters")
	private String eventFilters;

	@SerializedName("eventSignatures")
	private List<String> eventSignatures;

	@SerializedName("deliveryUrl")
	private String deliveryUrl;
@SerializedName("workflowId")
private String workflowId;

		@SerializedName("recurringType")
		private String recurringType;

	@SerializedName("abiEncoded")
	private String abiEncoded;

	@SerializedName("secret")
	private String secret;

	@SerializedName("chainId")
	private String chainId;
public WebhooksRequest(
		String name,
		String contractAddress,
		String eventFilters,
		List<String> eventSignatures,
		String deliveryUrl,
		String workflowId,
		String recurringType,
		String abiEncoded,
		String secret,
		String chainId
) {
	this.name = name;
	this.contractAddress = contractAddress;
	this.eventFilters = eventFilters;
	this.eventSignatures = eventSignatures;
	this.deliveryUrl = deliveryUrl;
	this.workflowId = workflowId;
	this.recurringType = recurringType;
	this.abiEncoded = abiEncoded;
	this.secret = secret;
	this.chainId = chainId;
}

	public String getName(){
		return name;
	}

	public String getContractAddress(){
		return contractAddress;
	}

	public String getEventFilters(){
		return eventFilters;
	}

	public List<String> getEventSignatures(){
		return eventSignatures;
	}

	public String getDeliveryUrl(){
		return deliveryUrl;
	}
public String getWorkflowId() {
	return workflowId;
}

		public String getRecurringType() {
			return recurringType;
		}

	public String getAbiEncoded(){
		return abiEncoded;
	}

	public String getSecret(){
		return secret;
	}

	public String getChainId(){
		return chainId;
	}
}