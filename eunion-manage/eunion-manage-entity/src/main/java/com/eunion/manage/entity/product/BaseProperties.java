package com.eunion.manage.entity.product;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by yangshuo on 2016/11/13.
 */
@Entity
@Table(name = "t_base_properties")
public class BaseProperties {

    private Long id;
    @Length(max = 50,message = "基本属性名称不能超过50个字符！")
    @NotNull(message = "基本属性名称不能为空！")
    private String name;
    @NotNull(message = "请选择数据类型名称")
    @Length(message = "数据类型名称超过3",max = 3)
    private String target;

    private String value;

    @Id
    @Column(name="id")
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
