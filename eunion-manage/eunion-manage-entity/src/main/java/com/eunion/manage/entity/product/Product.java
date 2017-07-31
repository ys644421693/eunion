package com.eunion.manage.entity.product;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Created by ys on 2016/10/23.
 */
@Entity
@Table(name = "t_product")
public class Product {
    private Long id;
    private int sort;

    @NotBlank(message = "商品名称不能为空！")
    @Length(max = 100,message = "商品名称不能超过100")
    private String name;

    @NotNull(message = "商品价格不能为空！")
    private BigDecimal price;
    private int quantity;
    private String description;
    private Date createTime = new Date();
    private String state="01";
    private boolean isHome;
    private String createUserId;
    private int salesVolume;
    private Set<PropertiesType> propertiesType;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false,nullable=false,length=20)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(length = 1)
    public boolean getIsHome() {
        return isHome;
    }

    public void setIsHome(boolean isHome) {
        this.isHome = isHome;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @Column(length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "t_product_type",
            joinColumns=@JoinColumn(name="productId"),
            inverseJoinColumns=@JoinColumn(name="propertiesId"))
    public Set<PropertiesType> getPropertiesType() {
        return propertiesType;
    }

    public void setPropertiesType(Set<PropertiesType> propertiesType) {
        this.propertiesType = propertiesType;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }
}
