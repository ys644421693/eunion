package com.eunion.manage.serviceimpl.system;

import com.eunion.manage.entity.table.ColumnsAndTable;
import com.eunion.manage.entity.table.Table;
import com.eunion.manage.service.system.ColumnsAndTablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ColumnsAndTablesServiceImpl implements ColumnsAndTablesService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${jdbc.db}")
    private String dbName;

    @Override
    public List<ColumnsAndTable> getAllolumnsByTable(String tableName) {
        String sql = "SELECT  * FROM information_schema.columns WHERE table_schema ='" + dbName + "' AND TABLE_NAME = " + tableName ;
        Object columnsAndTables = jdbcTemplate.query(sql, new ResultSetExtractor() {
            @Override
            public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ColumnsAndTable> list = new ArrayList<ColumnsAndTable>();

                while (rs.next()) {
                    ColumnsAndTable columnsAndTable = new ColumnsAndTable();
                    columnsAndTable.setCHARACTER_MAXIMUM_LENGTH(rs.getInt("CHARACTER_MAXIMUM_LENGTH"));
                    columnsAndTable.setCHARACTER_OCTET_LENGTH(rs.getInt("CHARACTER_OCTET_LENGTH"));
                    columnsAndTable.setCHARACTER_SET_NAME(rs.getString("CHARACTER_SET_NAME"));
                    columnsAndTable.setCOLLATION_NAME(rs.getString("COLLATION_NAME"));
                    columnsAndTable.setCOLUMN_COMMENT(rs.getString("COLUMN_COMMENT"));
                    columnsAndTable.setCOLUMN_DEFAULT(rs.getString("COLUMN_DEFAULT"));
                    columnsAndTable.setCOLUMN_KEY(rs.getString("COLUMN_KEY"));
                    columnsAndTable.setCOLUMN_NAME(rs.getString("COLUMN_NAME"));
                    columnsAndTable.setCOLUMN_TYPE(rs.getString("COLUMN_TYPE"));
                    columnsAndTable.setDATA_TYPE(rs.getString("DATA_TYPE"));
                    columnsAndTable.setDATETIME_PRECISION(rs.getInt("DATETIME_PRECISION"));
                    columnsAndTable.setEXTRA(rs.getString("EXTRA"));
                    columnsAndTable.setGENERATION_EXPRESSION(rs.getString("GENERATION_EXPRESSION"));
                    columnsAndTable.setIS_NULLABLE(rs.getString("IS_NULLABLE"));
                    columnsAndTable.setNUMERIC_PRECISION(rs.getInt("NUMERIC_PRECISION"));
                    columnsAndTable.setNUMERIC_SCALE(rs.getInt("NUMERIC_SCALE"));
                    columnsAndTable.setORDINAL_POSITION(rs.getInt("ORDINAL_POSITION"));
                    columnsAndTable.setPRIVILEGES(rs.getString("PRIVILEGES"));
                    columnsAndTable.setTABLE_CATALOG(rs.getString("TABLE_CATALOG"));
                    columnsAndTable.setTABLE_NAME(rs.getString("TABLE_NAME"));
                    columnsAndTable.setTABLE_SCHEMA(rs.getString("TABLE_SCHEMA"));
                    list.add(columnsAndTable);
                }
                return list;
            }
        });
        return (List<ColumnsAndTable>) columnsAndTables;
    }

    @Override
    public List<Table> getAllTable() {
        String sql = "SELECT  * FROM information_schema.TABLES WHERE table_schema ='" + dbName + "'";
        Object tables = jdbcTemplate.query(sql, new ResultSetExtractor() {
            @Override
            public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Table> list = new ArrayList<Table>();

                while (rs.next()) {
                    Table table = new Table();
                    table.setAUTO_INCREMENT(rs.getInt("AUTO_INCREMENT"));
                    table.setAVG_ROW_LENGTH(rs.getInt("AVG_ROW_LENGTH"));
                    table.setCHECK_TIME(rs.getString("CHECK_TIME"));
                    table.setCHECKSUM(rs.getInt("CHECKSUM"));
                    table.setCREATE_OPTIONS(rs.getString("CREATE_OPTIONS"));
                    table.setCREATE_TIME(rs.getString("CREATE_TIME"));
                    table.setDATA_FREE(rs.getInt("DATA_FREE"));
                    table.setDATA_LENGTH(rs.getInt("DATA_LENGTH"));
                    table.setENGINE(rs.getString("ENGINE"));
                    table.setINDEX_LENGTH(rs.getInt("INDEX_LENGTH"));
                    table.setMAX_DATA_LENGTH(rs.getInt("MAX_DATA_LENGTH"));
                    table.setROW_FORMAT(rs.getString("ROW_FORMAT"));
                    table.setTABLE_CATALOG(rs.getString("TABLE_CATALOG"));
                    table.setTABLE_COLLATION(rs.getString("TABLE_COLLATION"));
                    table.setTABLE_COMMENT(rs.getString("TABLE_COMMENT"));
                    table.setTABLE_NAME(rs.getString("TABLE_NAME"));
                    table.setTABLE_ROWS(rs.getInt("TABLE_ROWS"));
                    table.setTABLE_SCHEMA(rs.getString("TABLE_SCHEMA"));
                    table.setTABLE_TYPE(rs.getString("TABLE_TYPE"));
                    table.setUPDATE_TIME(rs.getString("UPDATE_TIME"));
                    table.setVERSION(rs.getInt("VERSION"));
                    list.add(table);
                }
                return list;
            }
        });
        return (List<Table>) tables;
    }
}
