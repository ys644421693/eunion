package com.eunion.manage.repository.role;
import com.eunion.manage.entity.sysrole.Role;
import com.eunion.manage.entity.sysrole.SystemUrl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by ys on 2016/4/15.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role,Long>{

    List<Role> findAll();

    List<Role> getRoleBySystemUrls(SystemUrl systemUrl);

    Role findById(long Id);
}
