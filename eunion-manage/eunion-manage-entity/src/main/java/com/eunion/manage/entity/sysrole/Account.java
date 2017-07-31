package com.eunion.manage.entity.sysrole;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name="t_account")
public class Account {
	private long id;
	@NotNull(message = "用户名称不能为空")
	@Length(message = "用户名称不能超过20位",max = 20)
	@Pattern(regexp = "^[0-9a-zA-Z_]+$",message="用户名只能是数字、字母、下划线组成")
	private String userName;
	@NotNull(message = "密码不能为空")
	@Length(message = "密码不能超过200位",max = 200)
	private String password;
	private Set<Role> roles;
	@Email(message = "邮箱格式错误！")
	private String email;

	@Id@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "userName",unique = true,length = 30)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password" ,length = 40)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable( name="account_role", joinColumns=@JoinColumn(name="account_id"), inverseJoinColumns=@JoinColumn(name="role_id") )
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
