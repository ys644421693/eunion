package com.eunion.manage.entity.table;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "t_component_factory")
public class ComponentFactory {

    private int id;
    private String content;
    private String protogenesis;
    //0 1 2 3
    private int type;
    private String serviceName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Lob
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Lob
    public String getProtogenesis() {
        return protogenesis;
    }

    public void setProtogenesis(String protogenesis) {
        this.protogenesis = protogenesis;
    }
}
