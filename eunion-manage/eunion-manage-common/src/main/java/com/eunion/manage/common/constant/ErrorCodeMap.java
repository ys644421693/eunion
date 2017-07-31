package com.eunion.manage.common.constant;

/**
 * Created by yangshuo on 2016/11/27.
 */
public enum ErrorCodeMap {

    PARAMS_CHECK_ERROR("参数错误！","000001");

    private String name;
    private String codeError;

    private ErrorCodeMap(String name, String codeError) {
        this.name = name;
        this.codeError = codeError;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeError() {
        return codeError;
    }

    public void setCodeError(String codeError) {
        this.codeError = codeError;
    }
}
