package in.mai.yourproject.api.workflowverify;

import com.google.gson.annotations.SerializedName;
import in.mai.yourproject.api.common.BaseResponse;

public class WorkflowVerifyResponse extends BaseResponse {

    @SerializedName("syntax_check")
    private boolean syntaxCheck;

    @SerializedName("schema_validation")
    private boolean schemaValidation;

    @SerializedName("error")
    private String error;

    public boolean isSyntaxCheck() {
        return syntaxCheck;
    }

    public boolean isSchemaValidation() {
        return schemaValidation;
    }

    public String getError() {
        return error;
    }
}

