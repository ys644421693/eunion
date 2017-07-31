package com.eunion.manage.product;

import com.eunion.manage.entity.product.BaseProperties;
import com.eunion.manage.service.product.BasePropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yangshuo on 2016/11/14.
 */
@Controller
@RequestMapping("/BaseProperties")
public class BasePropertiesController {

    @Autowired
    private BasePropertiesService basePropertiesService;

    @RequestMapping(value = "getAllBaseProperties",method = RequestMethod.GET)
    @ResponseBody
    public Object getAllBaseProperties(){
        return basePropertiesService.getAllBaseProperties();
    }

    @RequestMapping(value = "addProperties",method = RequestMethod.POST)
    @ResponseBody
    public Object addProperties(@RequestBody  BaseProperties baseProperties){
        return basePropertiesService.addProperties(baseProperties);
    }

    @RequestMapping(value = "updateProperties",method = RequestMethod.PUT)
    @ResponseBody
    public Object updateProperties(@RequestBody BaseProperties baseProperties){
        return basePropertiesService.addProperties(baseProperties);
    }

    @RequestMapping(value = "deleteProperties",method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteProperties(BaseProperties baseProperties){
        return basePropertiesService.deleteProperties(baseProperties);
    }
}
