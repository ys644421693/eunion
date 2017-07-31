package com.eunion.manage.repository.node;

import com.eunion.manage.entity.tree.TreeSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ys on 2016/4/27.
 */
@Repository
public interface TreeSystemRepository extends CrudRepository<TreeSystem,Long> {

    List<TreeSystem> findAll();
}
