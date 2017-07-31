package com.eunion.manage.service.role;

import com.eunion.manage.entity.sysrole.FieldCheck;

import java.util.List;

/**
 * Created by yangshuo on 2016/11/24.
 */
public interface FieldCheckService {

    List<FieldCheck> getAllFieldCheck();

    FieldCheck saveFieldCheck(FieldCheck fieldCheck);

    void deleteFieldCheck(FieldCheck fieldCheck);
}
