package com.eunion.portals;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args )
    {
        UserCode userCode = new UserCode();
        send();
    }
    //需要替换jce 8  jre\lib\security
    public static void send() {
        SimpleEmail email = new SimpleEmail();
        email.setStartTLSEnabled(true);

        email.setHostName("smtp.qq.com");
        email.setAuthentication("644421693", "ntorvondsaipbahd"); // 用户名和密码
        try {
            email.addTo("1345525969@qq.com"); // 接收方
            email.setFrom("644421693@qq.com"); // 发送方
            email.setSubject("测试发送邮件功能"); // 标题
            email.setMsg("测试发送邮件功能----杨硕"); // 内容
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
