package com.app.base.service;

import com.app.base.entity.User;

/**
 * Created by BF100365 on 2017/1/14.
 */
public interface UserService {

    User login(String userName,String password);
}
