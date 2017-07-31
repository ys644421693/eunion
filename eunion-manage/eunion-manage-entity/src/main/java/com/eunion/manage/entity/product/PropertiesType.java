package com.eunion.manage.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by ys on 2016/10/23.
 */
@Table(name = "t_properties_type")
@Entity
@JsonIgnoreProperties(value = {"product"})
public class PropertiesType {

    private Long id;
    @NotNull(message = "商品属性名称不能为空！")
    @Length(max = 50,message = "商品属性名称不能超过50字符")
    private String name;
    private int sort;
    private Long parentId;
    private String description;
    private String value;
    private Set<Product> product;
    private BaseProperties baseProperties;

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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(mappedBy="propertiesType",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public BaseProperties getBaseProperties() {
        return baseProperties;
    }

    public void setBaseProperties(BaseProperties baseProperties) {
        this.baseProperties = baseProperties;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
