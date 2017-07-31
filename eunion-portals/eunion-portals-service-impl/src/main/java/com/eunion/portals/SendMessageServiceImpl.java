package com.eunion.portals;

import com.eunion.portals.dto.MailDto;
import com.eunion.portals.service.SendMessageService;
import org.springframework.stereotype.Service;

/**
 * Created by ys on 2016/9/13.
 */
@Service("sendMessageService")
public class SendMessageServiceImpl implements SendMessageService {

    @Override
    public boolean sendEmail(MailDto mailDto) {
        StringBuffer content = new StringBuffer();
        content.append("客户邮箱："+mailDto.getMail()+"\r\n");
        content.append("客户姓名："+mailDto.getName()+"\r\n");
        content.append("内容："+mailDto.getMessage()+"\r\n");
        SendMessageCommon.send(mailDto.getSubject(),content.toString());
        return false;
    }
}
