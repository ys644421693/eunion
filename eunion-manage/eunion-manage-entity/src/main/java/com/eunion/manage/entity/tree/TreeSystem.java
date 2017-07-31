package com.eunion.manage.entity.tree;


import com.eunion.manage.entity.sysrole.Role;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ys on 2016/4/24.
 */
@Table(name = "t_tree_system")
@Entity
public class TreeSystem {
    private Long id;
    @NotBlank(message = "节点标识不能为空！")
    @Length(message = "节点标识不能超过50个字",max = 50)
    private String identify;
    @NotBlank(message = "节点名称不能为空！")
    @Length(message = "节点名称不能超过50个字",max = 50)
    private String treeName;
    private int parentId;
    private Set<Role> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable( name="t_tree_role", joinColumns=@JoinColumn(name="tree_id"), inverseJoinColumns=@JoinColumn(name="role_id") )
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
