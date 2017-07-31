package com.eunion.manage.repository;

import com.eunion.manage.entity.product.BaseProperties;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by yangshuo on 2016/11/13.
 */
public interface BasePropertiesRepository extends CrudRepository<BaseProperties, Long> {

    BaseProperties findById(BaseProperties baseProperties);

    List<BaseProperties> findAll();
}
