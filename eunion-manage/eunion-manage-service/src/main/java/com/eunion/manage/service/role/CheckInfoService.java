package com.eunion.manage.service.role;

import com.eunion.manage.entity.sysrole.CheckInfo;

import java.util.List;

/**
 * Created by yangshuo on 2016/11/22.
 */
public interface CheckInfoService {

    List<CheckInfo> getAllCheckInfo();

    Object saveCheckInfo(CheckInfo checkInfo);

    void deleteCheckInfo(CheckInfo checkInfo);
}
