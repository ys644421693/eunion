package com.eunion.manage.repository.role;

import com.eunion.manage.entity.sysrole.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ys on 2016/4/15.
 */
@Repository
public interface GroupRepository extends CrudRepository<Group,Long> {

}
