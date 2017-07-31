package com.eunion.manage.service.role;


import com.eunion.manage.entity.sysrole.Role;
import com.eunion.manage.entity.sysrole.SystemUrl;

import java.util.List;

/**
 * Created by ys on 2016/5/6.
 */
public interface RoleService {

    Object addRole(Role role);

    List<Role> getAllData();

    void deleteRole(Role role);

    List<Role> getRoleBySystemUrls(SystemUrl systemUrl);
}
