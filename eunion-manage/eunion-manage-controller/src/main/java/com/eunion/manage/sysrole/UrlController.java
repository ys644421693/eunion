package com.eunion.manage.sysrole;

import com.eunion.manage.common.constant.ErrorCodeMap;
import com.eunion.manage.common.util.ValidDataEntity;
import com.eunion.manage.dto.BaseResponse;
import com.eunion.manage.dto.request.UrlRoleDtoRequest;
import com.eunion.manage.dto.response.SystemUrlRespones;
import com.eunion.manage.entity.sysrole.SystemUrl;
import com.eunion.manage.service.role.UrlService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ys on 2016/5/8.
 */
@Controller
@RequestMapping("/data")
public class UrlController {

    private Logger logger = Logger.getLogger(RoleController.class);

    @Autowired
    private UrlService urlService;

    @RequestMapping(value = "/addUrl", method = RequestMethod.POST)
    @ResponseBody
    public Object addUrl(@RequestBody SystemUrl url) {
        return urlService.addUrl(url);
    }

    @RequestMapping(value = "/getAllUrl", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllData() {
        logger.info("---------------getAllRole---------------");
        return urlService.getAllData();
    }

    @RequestMapping(value = "deleteUrl", method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteUrl(SystemUrl url) {
        urlService.deleteUrl(url);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMsg("添加成功");
        baseResponse.setStatus("000000");
        return baseResponse;
    }

    @RequestMapping(value = "updateUrl", method = RequestMethod.PUT)
    @ResponseBody
    public Object updateUrl(@RequestBody SystemUrl url) {
        return urlService.updateUrl(url);
    }

    @RequestMapping(value = "addUrlRole", method = RequestMethod.POST)
    @ResponseBody
    public Object addUrlRole(@RequestBody UrlRoleDtoRequest urlRoleDtoRequest) {
        return urlService.addUrlRole(urlRoleDtoRequest);
    }

    @RequestMapping(value = "deleteUrlRole", method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteUrlRole(UrlRoleDtoRequest urlRoleDtoRequest) {
        return urlService.deleteUrlRole(urlRoleDtoRequest);
    }

}
