package com.eunion.manage.entity.sysrole;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by yangshuo on 2016/11/24.
 */
@Entity
@Table(name = "t_field_check")
public class FieldCheck {
    private Long id;

    @NotBlank(message = "参数名称不能为空！")
    @Length(message = "参数名称不能超过50个字",max = 50)
    private String name;

    @NotBlank(message = "描述不能为空！")
    @Length(message = "描述不能超过50个字",max = 50)
    private String description;
    private String type;
    private Set<CheckInfo> checkInfos;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ManyToMany(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinTable(name="t_field_check_info", joinColumns=@JoinColumn(name="field_id"), inverseJoinColumns=@JoinColumn(name="check_id") )
    public Set<CheckInfo> getCheckInfos() {
        return checkInfos;
    }

    public void setCheckInfos(Set<CheckInfo> checkInfos) {
        this.checkInfos = checkInfos;
    }

}
