package com.app.base.controller;

import com.app.base.common.HeadHandler;
import com.app.base.dto.MessageDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by BF100365 on 2016/12/28.
 */
@Controller
@RequestMapping("/message")
public class MessageController {



    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public Object sendMessage(MessageDto messageDto, HttpServletRequest request) {
        try {
            HeadHandler.sendUserMessage(messageDto.getContent() + "#" + request.getSession().getId(), messageDto.getAccessId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
