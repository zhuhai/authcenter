package com.zhuhai.common.constant;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/6/3
 * Time: 17:01
 */
public enum AuthResultConstant {

    SUCCESS(1, "success"),
    FAIL(0, "fail"),
    INVALID_CODE(10001, "Invalid code"),
    INVALID_USERNAME(10002,"Invalid userName");


    private int code;
    private String message;

    AuthResultConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
