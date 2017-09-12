package com.eunion.manage.service.system;

import com.eunion.manage.entity.system.TableServiceInfo;

public interface CustomerTableService {

    Object saveListData(TableServiceInfo tableServiceInfo);

    Object getAllData();
}
