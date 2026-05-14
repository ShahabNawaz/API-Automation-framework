package in.mai.yourproject.api.common;

public interface ApiEndPoints {

    String BALANCE = "/getbalance";
    String RECHARGE = "/recharge/v2";
    String DEPLOY_CONTRACT = "/contract/deploy";
    String DEPLOY_ESTIMATE = "/contract/deployEstimate";
    String SEND_TRANSACTION = "/transaction/send";
    String TRANSACTION_ESTIMATE = "/transaction/estimate";
    String EVENTS = "/events";
    String GET_SUPPORTED_TOKENS = "/supported/tokens";
    String GET_BALANCE = "/user/getBalance/";
    String GET_PRICE = "/getPrice";
    String WORKFLOW_VERIFY = "/workflow/verify";
    String WORKFLOW_STATUS = "/workflow/%s/status";
    String FETCH_ABI = "/contract/fetchABI";
    String GET_SUPPORTED_CHAINS = "/supported/chains";
    String BLOCK_MONITOR = "/block/monitor";
    String CHECK_PRICE = "/price/check";
    String FETCH_TOKENS_WITH_CHAIN_INFO = "/supported/tokensAndChains";
    String TRACKER = "/tracker";
    String TRACKER_STATUS = "/tracker/%s/status";
    String WEBHOOKS = "/webhooks";
    String TRACKER_EVENT = "/tracker/event";
    String TRACKER_PRICE = "/tracker/price";
    String GAS_FEES_LOGS_VIEW = "/gas-fees/logs/view/%s";
    String EXPLORER_ACTIONS = "/explorer/actions";
    String EXPLORER_ACTIONS_COUNT = "/explorer/actions/count";
    String EXPLORER_WORKFLOWS_DEPLOYED_COUNT = "/explorer/workflows/deployed/count";
    String WORKFLOW_STATUS_UPDATE = "/workflow/status/update";
    String WORKFLOW_DEPLOYER = "/workflow/deployer/%s";
    String WORKFLOW_DETAIL = "/workflow/%s/deployer/%s";
    String ACTION_LOG_VIEW = "/workflow/actionLog/view/deployer/%s";
    String ACTION_LOG_SUMMARY = "/workflow/actionLog/view/summary/%s";
    String ACTION_LOG_LIST = "/workflow/actionLog/view/list/%s";
    String WORKFLOW_CHAINCODE = "/workflow/chaincode/%s";
}

