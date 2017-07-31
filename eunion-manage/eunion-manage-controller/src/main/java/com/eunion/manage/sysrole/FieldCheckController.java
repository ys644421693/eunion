package com.eunion.manage.sysrole;

import com.eunion.manage.entity.sysrole.FieldCheck;
import com.eunion.manage.service.role.FieldCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yangshuo on 2016/11/24.
 */
@Controller
@RequestMapping("/fieldCheck")
public class FieldCheckController {

    @Autowired
    private FieldCheckService fieldCheckService;

    @ResponseBody
    @RequestMapping(value = "getAllFieldCheck",method = RequestMethod.GET)
    public Object getAllFieldCheck(){
        return  fieldCheckService.getAllFieldCheck();
    }

    @RequestMapping(value = "addFieldCheck",method = RequestMethod.POST)
    @ResponseBody
    public Object saveFieldCheck(FieldCheck fieldCheck){
        return fieldCheckService.saveFieldCheck(fieldCheck);
    }


    @RequestMapping(value = "updateFieldCheck",method = RequestMethod.PUT)
    @ResponseBody
    public Object updateFieldCheck(FieldCheck fieldCheck){
        return fieldCheckService.saveFieldCheck(fieldCheck);
    }

    @RequestMapping(value = "deleteFieldCheck",method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteFieldCheck(FieldCheck fieldCheck){
        fieldCheckService.saveFieldCheck(fieldCheck);
        return "ok";
    }
}
