package com.eunion.manage.entity.sysrole;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by yangshuo on 2016/11/22.
 */
@Entity
@Table(name = "t_check_info")
@JsonIgnoreProperties(value = {"fieldChecks"})
public class CheckInfo {

    private Long id;
    private String name;
    private String value;
    private String description;
    private Set<FieldCheck> fieldChecks;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER,mappedBy = "checkInfos")
    public Set<FieldCheck> getFieldChecks() {
        return fieldChecks;
    }

    public void setFieldChecks(Set<FieldCheck> fieldChecks) {
        this.fieldChecks = fieldChecks;
    }
}
