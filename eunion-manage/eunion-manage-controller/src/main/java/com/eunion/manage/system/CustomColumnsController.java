package com.eunion.manage.system;

import com.eunion.manage.entity.system.CustomColumns;
import com.eunion.manage.service.system.CustomColumnsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/table")
public class CustomColumnsController {
    private static Logger logger = org.apache.log4j.Logger.getLogger(CustomColumnsController.class);
    @Autowired
    private CustomColumnsService customColumnsService;

    @RequestMapping(value = "/saveDataTableInfo",method = RequestMethod.POST)
    @ResponseBody
    public Object saveDataTableInfo(List<CustomColumns> customColumns){
        return null;
    }
}
