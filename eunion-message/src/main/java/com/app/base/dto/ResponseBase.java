package com.app.base.dto;

/**
 * Created by BF100365 on 2017/1/14.
 */
public class ResponseBase {

    private String status;
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
