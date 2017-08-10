package com.eunion.manage.service.system;

import com.eunion.manage.dto.response.TableInfoRespones;
import com.eunion.manage.entity.table.ColumnsAndTable;
import com.eunion.manage.entity.table.Table;

import java.util.List;

public interface ColumnsAndTablesService {

    List<ColumnsAndTable> getAllColumnsByTable(String tableName);

    List<Table> getAllTable();

    List<TableInfoRespones> getTableInfo();
}
