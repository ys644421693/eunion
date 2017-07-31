package com.eunion.manage.entity.sysrole;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ys on 2016/4/21.
 */
@Table(name = "t_system_url")
@Entity
public class SystemUrl {

    private long id;
    @NotBlank(message = "url名称不能为空！")
    @Length(message = "url名称不能超过50个字",max = 50)
    private String urlName;

    @NotBlank(message = "url不能为空！")
    @Length(message = "url不能超过50个字",max = 50)
    private String url;

    @NotBlank(message = "描述不能为空！")
    @Length(message = "描述不能超过50个字",max = 50)
    private String descript;
    private Set<Role> roles;
    private Set<FieldCheck> fieldCheck;
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(length = 20)
    public String getUrlName() {
        return urlName;
    }


    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
    @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable( name="url_role", joinColumns=@JoinColumn(name="url_id"), inverseJoinColumns=@JoinColumn(name="role_id") )
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable( name="t_field_url", joinColumns=@JoinColumn(name="url_id"), inverseJoinColumns=@JoinColumn(name="field_id") )
    public Set<FieldCheck> getFieldCheck() {
        return fieldCheck;
    }

    public void setFieldCheck(Set<FieldCheck>  fieldCheck) {
        this.fieldCheck = fieldCheck;
    }
}
