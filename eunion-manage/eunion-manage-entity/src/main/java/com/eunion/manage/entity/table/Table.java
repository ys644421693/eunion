package com.eunion.manage.entity.table;

public class Table {

    private String TABLE_CATALOG;
    private String TABLE_SCHEMA;
    private String TABLE_NAME;
    private String TABLE_TYPE;
    private String ENGINE;
    private int VERSION;
    private String ROW_FORMAT;
    private int TABLE_ROWS;
    private int AVG_ROW_LENGTH;
    private int DATA_LENGTH;
    private int MAX_DATA_LENGTH;
    private int INDEX_LENGTH;
    private int DATA_FREE;
    private int AUTO_INCREMENT;
    private String CREATE_TIME;
    private String UPDATE_TIME;
    private String CHECK_TIME;
    private String TABLE_COLLATION;
    private int CHECKSUM;
    private String CREATE_OPTIONS;
    private String TABLE_COMMENT;

    public String getTABLE_CATALOG() {
        return TABLE_CATALOG;
    }

    public void setTABLE_CATALOG(String TABLE_CATALOG) {
        this.TABLE_CATALOG = TABLE_CATALOG;
    }

    public String getTABLE_SCHEMA() {
        return TABLE_SCHEMA;
    }

    public void setTABLE_SCHEMA(String TABLE_SCHEMA) {
        this.TABLE_SCHEMA = TABLE_SCHEMA;
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public void setTABLE_NAME(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    public String getTABLE_TYPE() {
        return TABLE_TYPE;
    }

    public void setTABLE_TYPE(String TABLE_TYPE) {
        this.TABLE_TYPE = TABLE_TYPE;
    }

    public String getENGINE() {
        return ENGINE;
    }

    public void setENGINE(String ENGINE) {
        this.ENGINE = ENGINE;
    }

    public int getVERSION() {
        return VERSION;
    }

    public void setVERSION(int VERSION) {
        this.VERSION = VERSION;
    }

    public String getROW_FORMAT() {
        return ROW_FORMAT;
    }

    public void setROW_FORMAT(String ROW_FORMAT) {
        this.ROW_FORMAT = ROW_FORMAT;
    }

    public int getTABLE_ROWS() {
        return TABLE_ROWS;
    }

    public void setTABLE_ROWS(int TABLE_ROWS) {
        this.TABLE_ROWS = TABLE_ROWS;
    }

    public int getAVG_ROW_LENGTH() {
        return AVG_ROW_LENGTH;
    }

    public void setAVG_ROW_LENGTH(int AVG_ROW_LENGTH) {
        this.AVG_ROW_LENGTH = AVG_ROW_LENGTH;
    }

    public int getDATA_LENGTH() {
        return DATA_LENGTH;
    }

    public void setDATA_LENGTH(int DATA_LENGTH) {
        this.DATA_LENGTH = DATA_LENGTH;
    }

    public int getMAX_DATA_LENGTH() {
        return MAX_DATA_LENGTH;
    }

    public void setMAX_DATA_LENGTH(int MAX_DATA_LENGTH) {
        this.MAX_DATA_LENGTH = MAX_DATA_LENGTH;
    }

    public int getINDEX_LENGTH() {
        return INDEX_LENGTH;
    }

    public void setINDEX_LENGTH(int INDEX_LENGTH) {
        this.INDEX_LENGTH = INDEX_LENGTH;
    }

    public int getDATA_FREE() {
        return DATA_FREE;
    }

    public void setDATA_FREE(int DATA_FREE) {
        this.DATA_FREE = DATA_FREE;
    }

    public int getAUTO_INCREMENT() {
        return AUTO_INCREMENT;
    }

    public void setAUTO_INCREMENT(int AUTO_INCREMENT) {
        this.AUTO_INCREMENT = AUTO_INCREMENT;
    }

    public String getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(String CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public String getUPDATE_TIME() {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(String UPDATE_TIME) {
        this.UPDATE_TIME = UPDATE_TIME;
    }

    public String getCHECK_TIME() {
        return CHECK_TIME;
    }

    public void setCHECK_TIME(String CHECK_TIME) {
        this.CHECK_TIME = CHECK_TIME;
    }

    public String getTABLE_COLLATION() {
        return TABLE_COLLATION;
    }

    public void setTABLE_COLLATION(String TABLE_COLLATION) {
        this.TABLE_COLLATION = TABLE_COLLATION;
    }

    public int getCHECKSUM() {
        return CHECKSUM;
    }

    public void setCHECKSUM(int CHECKSUM) {
        this.CHECKSUM = CHECKSUM;
    }

    public String getCREATE_OPTIONS() {
        return CREATE_OPTIONS;
    }

    public void setCREATE_OPTIONS(String CREATE_OPTIONS) {
        this.CREATE_OPTIONS = CREATE_OPTIONS;
    }

    public String getTABLE_COMMENT() {
        return TABLE_COMMENT;
    }

    public void setTABLE_COMMENT(String TABLE_COMMENT) {
        this.TABLE_COMMENT = TABLE_COMMENT;
    }
}
