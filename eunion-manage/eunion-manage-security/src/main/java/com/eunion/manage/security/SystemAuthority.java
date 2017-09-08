package com.eunion.manage.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ys on 2016/4/18.
 */
@Controller
public class SystemAuthority {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SystemUserDetailService systemUserDetailService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome() {
        return "Success";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "/admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        String userName, String password, HttpServletRequest request) {
        if (error != null && error.contains("true")) {
            logger.debug("-------------登录失败--------------");
            return "redirect:/login.html?error=true";
        }
        if (logout != null && logout.contains("true")) {
            logger.debug("-------------登录失败--------------");
            return "redirect:/login.html?logout=true";
        }
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            /*HttpSession session = request.getSession();
            session.setAttribute("", SecurityContextHolder.getContext());*/
            return "redirect:/system/admin";
        } catch (Exception ex) {
            return "/system/login?error=true";
        }
    }

}
