package com.eunion.manage.repository.role;

import com.eunion.manage.entity.sysrole.SystemUrl;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ys on 2016/4/21.
 */
public interface SystemUrlRepository extends CrudRepository<SystemUrl,Long> {

    List<SystemUrl> findAll();

    SystemUrl findById(long Id);

    SystemUrl findByUrl(String url);
}
