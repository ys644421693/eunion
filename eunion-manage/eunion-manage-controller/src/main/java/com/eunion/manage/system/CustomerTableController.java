package com.eunion.manage.system;

import com.eunion.manage.entity.system.TableServiceInfo;
import com.eunion.manage.service.system.CustomerTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data")
public class CustomerTableController {

    @Autowired
    private CustomerTableService customerTableService;

    @ResponseBody
    @RequestMapping(value = "/saveAllData",method = RequestMethod.GET)
    public Object saveAllData(TableServiceInfo tableServiceInfo){
        return  customerTableService.saveListData(tableServiceInfo);
    }
}
