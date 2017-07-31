package com.eunion.manage.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by ys on 2016/4/17.
 */
public class SystemAccessDecisionManager implements AccessDecisionManager {
    protected final Log logger = LogFactory.getLog(this.getClass());
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        Collection<? extends GrantedAuthority>authorities=authentication.getAuthorities();
        if(configAttributes==null){
            return ;
        }
        if(authorities==null||authorities.isEmpty()){
            throw new AccessDeniedException("无权限");
        }
        logger.debug("---------------请求地址：【" + object.toString()+"】-------------------"); //object is a URL.
        Iterator<ConfigAttribute> ite=configAttributes.iterator();
        while(ite.hasNext()){
             ConfigAttribute ca=ite.next();
            String needRole=((SecurityConfig)ca).getAttribute();
            for(GrantedAuthority ga:authorities){
                if(needRole.equals(ga.getAuthority())){ //ga is user's sysrole.
                    return;
                }
            }
        }
        throw new AccessDeniedException("没有访问权限！");
    }

    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    public boolean supports(Class<?> aClass) {
        return true;
    }
}
