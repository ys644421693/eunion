package com.eunion.manage.entity.system;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Table(name = "t_custom_column")
@Entity
@JsonIgnoreProperties(value = {"tableServiceInfo"})
public class CustomColumns {

    private int id;
    private String columnName;
    private int indexs;
    private boolean isShows;
    private String alias;//别名
    private boolean isSorts;
    private Set<TransferredMeaning> transferredMeanings;
    private TableServiceInfo tableServiceInfo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getIndexs() {
        return indexs;
    }

    public void setIndexs(int indexs) {
        this.indexs = indexs;
    }

    public boolean isShows() {
        return isShows;
    }

    public void setShows(boolean shows) {
        isShows = shows;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean isSorts() {
        return isSorts;
    }

    public void setSorts(boolean sorts) {
        isSorts = sorts;
    }

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, targetEntity = TransferredMeaning.class, mappedBy = "customColumns")
    public Set<TransferredMeaning> getTransferredMeanings() {
        return transferredMeanings;
    }

    public void setTransferredMeanings(Set<TransferredMeaning> transferredMeanings) {
        this.transferredMeanings = transferredMeanings;
    }
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "tableServiceInfoId")
    public TableServiceInfo getTableServiceInfo() {
        return tableServiceInfo;
    }

    public void setTableServiceInfo(TableServiceInfo tableServiceInfo) {
        this.tableServiceInfo = tableServiceInfo;
    }
}
