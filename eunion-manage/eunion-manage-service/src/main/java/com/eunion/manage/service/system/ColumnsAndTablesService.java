package com.eunion.manage.service.system;

import com.eunion.manage.entity.table.ColumnsAndTable;
import com.eunion.manage.entity.table.Table;

import java.util.List;

public interface ColumnsAndTablesService {

    List<ColumnsAndTable> getAllolumnsByTable(String tableName);

    List<Table> getAllTable();
}
