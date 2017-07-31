package com.eunion.portals.controller;

import com.eunion.portals.dto.MailDto;
import com.eunion.portals.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ys on 2016/9/3.
 */
@RestController
@RequestMapping("/info")
public class InterfaceController {

    @Autowired
    private SendMessageService sendMessageService;

    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    @ResponseBody
    public Object sendMail(@RequestBody MailDto mailDto) {

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            sendMessageService.sendEmail(mailDto);
            map.put("msg", "Email has been sent. Our customer service will contact you later.");
            map.put("state", "000000");
        }catch (Exception ex){
            map.put("msg","Email fail to send. Please try again." );
            map.put("state", "999999");
        }

        return map;
    }
}
