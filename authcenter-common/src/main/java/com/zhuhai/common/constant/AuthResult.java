package com.zhuhai.common.constant;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/6/1
 * Time: 22:17
 */
public class AuthResult {

    private int code;
    private String message;
    private Object data;

    public AuthResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public AuthResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public AuthResult(AuthResultConstant authResultConstant) {
        this.code = authResultConstant.getCode();
        this.message = authResultConstant.getMessage();
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
