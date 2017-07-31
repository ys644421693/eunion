package com.eunion.manage.service.role;

import com.eunion.manage.entity.sysrole.Account;

import java.util.List;

/**
 * Created by yangshuo on 16/2/8.
 */
public interface UserService {

    public void add(Account account);

    List<Account> listUser();
}
