package com.eunion.manage.entity.sysrole;

import com.eunion.manage.entity.tree.TreeSystem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ys on 2016/4/14.
 */
@Entity
@Table(name = "t_role")
@JsonIgnoreProperties(value = {"treeSystems","systemUrls","groups","accounts"})
public class Role {
    private long id;
    @NotBlank(message = "权限名称不能为空！")
    @Length(message = "权限名称不能超过50个字",max = 50)
    private  String roleName;

    @NotBlank(message = "描述不能为空！")
    @Length(message = "描述不能超过50个字",max = 50)
    private  String descript;

    @NotBlank(message = "分类标识不能为空！")
    @Length(message = "分类标识不能超过50个字",max = 50)
    private  String category;
    private Set<Account> accounts;
    private  Set<Group> groups;
    private  Set<SystemUrl> systemUrls;
    private Set<TreeSystem> treeSystems;
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @ManyToMany(mappedBy="roles",cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    public Set<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @ManyToMany(mappedBy="roles",cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @ManyToMany(mappedBy="roles",cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    public Set<SystemUrl> getSystemUrls() {
        return systemUrls;
    }

    public void setSystemUrls(Set<SystemUrl> systemUrls) {
        this.systemUrls = systemUrls;
    }

    @ManyToMany(mappedBy="roles",cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    public Set<TreeSystem> getTreeSystems() {
        return treeSystems;
    }

    public void setTreeSystems(Set<TreeSystem> treeSystems) {
        this.treeSystems = treeSystems;
    }
}
