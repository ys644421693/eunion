package com.eunion.manage.service.system;

import com.eunion.manage.entity.system.CustomColumns;

import java.util.List;

public interface CustomColumnsService {

    List<CustomColumns> getByTableName(String tableName);

    Object save(List<CustomColumns> customColumns);
}
