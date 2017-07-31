package com.app.base.entity;

/**
 * Created by BF100365 on 2017/1/14.
 */
public class User {
    private String userName;
    //客服类型
    private String type;
    //头像图片地址
    private String headPic;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }
}
