package in.mai.yourproject.api.oraclepricewebhook;

import com.google.gson.annotations.SerializedName;

public class OraclePriceWebhookRequest {

	@SerializedName("name")
	private String name;

	@SerializedName("deliveryUrl")
	private String deliveryUrl;

	@SerializedName("secret")
	private String secret;

	@SerializedName("contractAddress")
	private String contractAddress;

	@SerializedName("chainId")
	private String chainId;

	@SerializedName("price")
	private String price;

	@SerializedName("workflowId")
	private String workflowId;

	@SerializedName("recurringType")
	private String recurringType;

	public String getName(){
		return name;
	}

	public String getDeliveryUrl(){
		return deliveryUrl;
	}

	public String getSecret(){
		return secret;
	}

	public String getContractAddress(){
		return contractAddress;
	}

	public String getChainId(){
		return chainId;
	}

	public String getPrice(){
		return price;
	}

	public String getWorkflowId(){
		return workflowId;
	}

	public String getRecurringType(){
		return recurringType;
	}

	public OraclePriceWebhookRequest(String name, String deliveryUrl,String workflowId, String recurringType, String secret, String contractAddress, String chainId, String price) {
		this.name = name;
		this.deliveryUrl = deliveryUrl;
		this.workflowId = workflowId;
		this.recurringType = recurringType;
		this.secret = secret;
		this.contractAddress = contractAddress;
		this.chainId = chainId;
		this.price = price;
	}
}

