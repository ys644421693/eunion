package com.app.base.service.impl;

import com.app.base.entity.User;
import com.app.base.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by BF100365 on 2017/1/14.
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User login(String userName, String password) {
        User user = new User();
        user.setHeadPic("");
        user.setType("01");
        user.setUserName("zhangsan");
        return user;
    }
}
