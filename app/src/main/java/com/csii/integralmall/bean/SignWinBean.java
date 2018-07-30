package com.csii.integralmall.bean;

public class SignWinBean {
    /**
     * code : 200
     * message : 签到成功!!
     * result : http://192.168.1.198:7777/swagger/htmls/refershcard/fanpai.html
     * success : true
     * timestamp : 1532416136241
     */

    private int code;
    private String message;
    private String result;
    private boolean success;
    private long timestamp;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
