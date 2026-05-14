package in.mai.yourproject.api.common;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    public  boolean ok;
    public int statusCode;

    public String getCode() {
        return code;
    }

    public String code;
    public String errorMessage;
    public String errorReason;
    public long time;

    @SerializedName("message")
    private String message;

    @SerializedName("msg")
    private String massage;

    @SerializedName("id")
    private int id;

    public String getErrorMsg() {
        return message;
    }

    public String getMessage() {
        return message;
    }
    public String getMsg(){
        return massage;
    }


    public int getId(){
        return id;
    }

}
