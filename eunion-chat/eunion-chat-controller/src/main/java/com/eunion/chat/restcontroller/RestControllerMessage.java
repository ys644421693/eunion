package com.eunion.chat.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ys on 2016/7/10.
 */
@RestController
public class RestControllerMessage {

    @RequestMapping("/test")
    public Object testRest(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhangsan");
        return map;
    }
}
