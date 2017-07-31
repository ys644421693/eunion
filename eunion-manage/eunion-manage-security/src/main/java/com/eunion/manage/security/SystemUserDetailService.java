package com.eunion.manage.security;


import com.eunion.manage.entity.sysrole.Account;
import com.eunion.manage.entity.sysrole.Role;
import com.eunion.manage.repository.role.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 从数据库中读入用户的密码，角色信息，是否锁定，账号是否过期等
 * Created by ys on 2016/4/17.
 */
public class SystemUserDetailService implements UserDetailsService {

    @Resource
    private AccountRepository accountRepository;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        if("".equals(userName)){
            return null;
        }
        Collection<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        Account account = accountRepository.findByUserName(userName);
        for(Role role : account.getRoles()) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            auth.add(authority);
        }
        User user = new User(userName,account.getPassword(),true,true,true,true,auth);
        return user;
    }

    /*private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(UserRole userRole : userRoles){
            //凭证
            authorities.add(new SimpleGrantedAuthority("123"));
        }
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(authorities);
        return  result;
    }*/

    private  User buildUserForAuthentication(com.eunion.manage.entity.User user,List<GrantedAuthority> authorities){
        return new User(user.getUserName(),user.getUserPassword(),true,true,true,true,authorities);
    }
}
