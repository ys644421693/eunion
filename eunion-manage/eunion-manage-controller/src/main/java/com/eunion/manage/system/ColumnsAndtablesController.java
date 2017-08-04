package com.eunion.manage.system;

import com.eunion.manage.entity.table.ColumnsAndTable;
import com.eunion.manage.entity.table.Table;
import com.eunion.manage.service.system.ColumnsAndTablesService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/table")
@Log4j
public class ColumnsAndtablesController {

    @Autowired
    private ColumnsAndTablesService columnsAndTablesService;

    @RequestMapping(value = "/getAllTable",method = RequestMethod.GET)
    @ResponseBody
    public List<Table> getAllTable(){
        return columnsAndTablesService.getAllTable();
    }

    @RequestMapping(value = "/getAllColumnsByTable",method = RequestMethod.GET)
    @ResponseBody
    public List<ColumnsAndTable> getAllColumnsByTable(String tableName){
        return columnsAndTablesService.getAllolumnsByTable(tableName);
    }
}
