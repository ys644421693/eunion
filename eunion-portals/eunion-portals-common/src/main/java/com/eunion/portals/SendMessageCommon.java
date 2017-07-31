package com.eunion.portals;

import com.eunion.portals.common.Config;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Created by ys on 2016/9/13.
 */
public class SendMessageCommon {

    //需要替换jce 8  jre\lib\security
    public static void send(String subject,String content) {
        SimpleEmail email = new SimpleEmail();
        email.setStartTLSEnabled(true);

        email.setHostName(Config.getValueByName("HostName"));
        email.setAuthentication(Config.getValueByName("Authentication"), Config.getValueByName("password")); // 用户名和密码
        try {
            email.addTo(Config.getValueByName("address")); // 接收方
            email.setFrom(Config.getValueByName("sender")); // 发送方
            email.setSubject(subject); // 标题
            email.setMsg(content); // 内容
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
