package com.eunion.manage.entity.sysrole;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ys on 2016/4/14.
 */
@Entity
@Table(name = "t_group")
public class Group {
    private long id;
    private String groupName;
    private Set<Role> roles;
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable( name="group_role", joinColumns=@JoinColumn(name="group_id"), inverseJoinColumns=@JoinColumn(name="role_id") )
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
