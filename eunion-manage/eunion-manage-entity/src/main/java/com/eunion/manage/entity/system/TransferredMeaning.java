package com.eunion.manage.entity.system;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TransferredMeaning {

    private int id;
    private String value;
    private String valueOriginal;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueOriginal() {
        return valueOriginal;
    }

    public void setValueOriginal(String valueOriginal) {
        this.valueOriginal = valueOriginal;
    }

}
