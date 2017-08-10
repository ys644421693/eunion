package com.eunion.manage.system;

import com.eunion.manage.service.system.ComponentFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/component")
@Controller
public class ComponentFactoryController {

    @Autowired
    private ComponentFactoryService componentFactoryService;

    @ResponseBody
    @RequestMapping(value = "/getAllComponent",method = RequestMethod.GET)
    public Object getAllComponent(){
        return componentFactoryService.getAllComponent();
    }

}
