package com.app.base.dto;

import java.io.Serializable;

/**
 * Created by BF100365 on 2016/12/16.
 */
public class ServiceSessionInfo implements Serializable {

    private String sessionId;
    //请求地址
    private String localAddress;
    //头像图片地址
    private String headPic;
    //客服名称
    private String name;
    //文本消息大小
    private int textMessageSizeLimit;
    private String uri;
    private String principalName;
    //客服类型
    private String type;

    private String socketId;


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public int getTextMessageSizeLimit() {
        return textMessageSizeLimit;
    }

    public void setTextMessageSizeLimit(int textMessageSizeLimit) {
        this.textMessageSizeLimit = textMessageSizeLimit;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSocketId() {
        return socketId;
    }

    public void setSocketId(String socketId) {
        this.socketId = socketId;
    }
}
