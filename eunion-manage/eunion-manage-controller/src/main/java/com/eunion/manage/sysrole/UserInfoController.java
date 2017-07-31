package com.eunion.manage.sysrole;

import com.eunion.manage.entity.sysrole.Account;
import com.eunion.manage.service.role.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by yangshuo on 16/1/27.
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUser")
    public String getUserInfo(){
        System.out.println("controller");
        Account account = new Account();
        account.setId(8);
        account.setUserName("zhangshan");
        account.setPassword("1234567");
        userService.add(account);
        return "UserInfo";
    }

    @RequestMapping("getUserAll")
    public String getUserList(){
        List<Account> accountList = userService.listUser();
        for(Account account : accountList){
            System.out.println(account.getId());
        }
        return "UserInfo";
    }
}
