package com.app.base.controller;

import com.app.base.dto.ResponseBase;
import com.app.base.dto.ServiceSessionInfo;
import com.app.base.entity.User;
import com.app.base.service.ServiceInfoCache;
import com.app.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by BF100365 on 2017/1/14.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    public static String sessionId = null;

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceInfoCache serviceInfoCache;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody(required = true) String userName, @RequestBody(required = true) String password, HttpServletRequest request) {
        ResponseBase responseBase = new ResponseBase();
        ServiceSessionInfo serviceSessionInfo = new ServiceSessionInfo();
        //登录成功放入客服缓存中
        User user = userService.login(userName, password);

        serviceSessionInfo.setSessionId(request.getSession().getId());
        serviceSessionInfo.setName(user.getUserName());
        serviceSessionInfo.setType(user.getType());
        serviceSessionInfo.setHeadPic(user.getHeadPic());

        serviceInfoCache.put(serviceSessionInfo);
        sessionId = serviceSessionInfo.getSessionId();
        responseBase.setStatus("000000");
        responseBase.setMsg("登录成功！");

        return responseBase;
    }

    @RequestMapping(value = "/getOnlineServer", method = RequestMethod.GET)
    @ResponseBody
    public Object getOnlineServer() {
        return serviceInfoCache.getAll();
    }

}
