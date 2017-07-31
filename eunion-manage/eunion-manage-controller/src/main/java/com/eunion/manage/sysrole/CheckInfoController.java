package com.eunion.manage.sysrole;

import com.eunion.manage.entity.sysrole.CheckInfo;
import com.eunion.manage.service.role.CheckInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yangshuo on 2016/11/22.
 */
@Controller
@RequestMapping("/checkInfo")
public class CheckInfoController {

    @Autowired
    private CheckInfoService checkInfoService;

    @RequestMapping(value = "getAllCheckInfo",method = RequestMethod.GET)
    @ResponseBody
    public Object getAllCheckInfo(){
        return checkInfoService.getAllCheckInfo();
    }

    @RequestMapping(value = "saveCheckInfo",method = RequestMethod.POST)
    @ResponseBody
    public Object saveCheckInfo(@RequestBody CheckInfo checkInfo){
        return checkInfoService.saveCheckInfo(checkInfo);
    }

    @RequestMapping(value = "updateCheckInfo",method = RequestMethod.PUT)
    @ResponseBody
    public Object updateCheckInfo(@RequestBody CheckInfo checkInfo){
        return checkInfoService.saveCheckInfo(checkInfo);
    }

    @RequestMapping(value = "deleteCheckInfo",method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteCheckInfo(CheckInfo checkInfo){
        checkInfoService.deleteCheckInfo(checkInfo);
        return null;
    }
}
