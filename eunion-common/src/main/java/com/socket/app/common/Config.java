package com.socket.app.common;

/**
 * Created by ys on 2016/9/13.
 */
public enum Config {
    //admin@e-uniontech.com
    HOSTNAME("HostName","smtp.qq.com" ),
    AUTHENTICATION("Authentication","644421693"),
    SENDER("sender","644421693@qq.com"),
    PASSWORD("password","ntorvondsaipbahd"),
    ADDRESS("address", "admin@e-uniontech.com");

    // 成员变量
    private String name;
    private String value;

    // 构造方法
    private Config(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static String getValueByName(String name){
        for (Config config : Config.values()){
            if (config.getName().equals(name)){
                return config.getValue();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
