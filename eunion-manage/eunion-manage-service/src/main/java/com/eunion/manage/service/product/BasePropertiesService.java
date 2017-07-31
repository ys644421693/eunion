package com.eunion.manage.service.product;

import com.eunion.manage.entity.product.BaseProperties;

import java.util.List;

/**
 * Created by yangshuo on 2016/11/14.
 */
public interface BasePropertiesService {

    List<BaseProperties> getAllBaseProperties();

    Object addProperties(BaseProperties baseProperties);

    Object deleteProperties(BaseProperties baseProperties);
}
