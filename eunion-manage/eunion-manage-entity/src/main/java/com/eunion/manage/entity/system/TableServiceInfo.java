package com.eunion.manage.entity.system;

import javax.persistence.*;
import java.util.Set;

@Table(name = "t_table_service_info")
@Entity
public class TableServiceInfo {

    private int id;
    private String tableName;
    private String serviceSpace;
    private String serviceName;
    private boolean pageFlag;
    private int pageSize;
    private String tableClass;
    private Set<CustomColumns> customColumns;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getServiceSpace() {
        return serviceSpace;
    }

    public void setServiceSpace(String serviceSpace) {
        this.serviceSpace = serviceSpace;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public boolean isPageFlag() {
        return pageFlag;
    }

    public void setPageFlag(boolean pageFlag) {
        this.pageFlag = pageFlag;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getTableClass() {
        return tableClass;
    }

    public void setTableClass(String tableClass) {
        this.tableClass = tableClass;
    }
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, targetEntity = CustomColumns.class, mappedBy = "tableServiceInfo")
    public Set<CustomColumns> getCustomColumns() {
        return customColumns;
    }

    public void setCustomColumns(Set<CustomColumns> customColumns) {
        this.customColumns = customColumns;
    }
}
