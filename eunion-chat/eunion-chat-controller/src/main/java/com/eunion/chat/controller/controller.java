package com.eunion.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ys on 2016/7/10.
 */
@Controller
public class controller {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
