package com.eunion.manage.entity.system;

import javax.persistence.*;
import java.util.Set;

@Table(name = "t_custom_column")
@Entity
public class CustomColumns {

    private int id;
    private String tableName;
    private String columnName;
    private int indexs;
    private boolean isShows;
    private String alias;//别名
    private boolean isSorts;
    private Set<TransferredMeaning> transferredMeanings;

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

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable( name="t_field_url", joinColumns=@JoinColumn(name="url_id"), inverseJoinColumns=@JoinColumn(name="field_id") )
    public Set<TransferredMeaning> getTransferredMeanings() {
        return transferredMeanings;
    }

    public void setTransferredMeanings(Set<TransferredMeaning> transferredMeanings) {
        this.transferredMeanings = transferredMeanings;
    }
}
