package com.eunion.manage.security;

import com.eunion.manage.entity.sysrole.Role;
import com.eunion.manage.entity.sysrole.SystemUrl;
import com.eunion.manage.repository.role.SystemUrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.annotation.Resource;
import java.io.IOError;
import java.io.IOException;
import java.util.*;

/**
 * 此类在初始化时，应该取到所有资源及其对应角色的定义
 * Created by ys on 2016/4/17.
 */
public class SystemInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
    private static Map<String,String> interfaceUrl = new HashMap<>();

    @Resource
    private SystemUrlRepository systemUrlRepository;

    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // object 是一个URL，被用户请求的url。
        String url = ((FilterInvocation) object).getRequestUrl();
        logger.debug("url:" + url);
        int firstQuestionMarkIndex = url.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            url = url.substring(0, firstQuestionMarkIndex);
        }
        /*Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            if (url.equals(resURL)) {
                return resourceMap.get(resURL);
            }
        }*/
        if (resourceMap.get(url) != null){
            Collection<ConfigAttribute> configAttributes = resourceMap.get(url);
            return configAttributes;
        }

        //对外的接口开放所有权限
        if ("INTERFACE_ROLE".equals(interfaceUrl.get(url))){
            return null;
        }

        //默认为admin权限
        Collection<ConfigAttribute> att = new ArrayList<ConfigAttribute>();
        ConfigAttribute ca = new SecurityConfig("admin");
        att.add(ca);
        resourceMap.put(url,att);

        return att;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public boolean supports(Class<?> aClass) {
        loadResourceDefine();
        return true;
    }

    private void loadResourceDefine() {
        List<SystemUrl> systemUrls =  systemUrlRepository.findAll();
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        for(SystemUrl systemUrl : systemUrls){
            Collection<ConfigAttribute> att = new ArrayList<ConfigAttribute>();
            for(Role role : systemUrl.getRoles()){
                if (role.getRoleName().equals("INTERFACE_ROLE")){
                    interfaceUrl.put(systemUrl.getUrl(),"INTERFACE_ROLE");
                    continue;
                }
                ConfigAttribute ca = new SecurityConfig(role.getRoleName());
                att.add(ca);
            }
            if (att.isEmpty()){
                ConfigAttribute ca = new SecurityConfig("SECURITY_ROLE");
                att.add(ca);
            }
            resourceMap.put(systemUrl.getUrl(), att);
        }
    }

    /**
     * 删除权限本地缓存 立即生效
     * @param roleName
     * @param url
     * @return
     */
    public static boolean removeUrlRole(String roleName,String url){
        Collection<ConfigAttribute> att = resourceMap.get(url);
        if (att == null){
            return false;
        }
        for (ConfigAttribute configAttribute : att){
            if (configAttribute.getAttribute().equals(roleName)){
                att.remove(configAttribute);
                return true;
            }
        }
        return false;
    }
    /**
     * 添加新权限到本地缓存中立即生效
     * @param roleName
     * @param url
     * @return
     */
    public static boolean addUrlRole(String roleName,String url){
        Collection<ConfigAttribute> att = resourceMap.get(url);
        if (att == null){
            att = new ArrayList<ConfigAttribute>();
        }
        ConfigAttribute ca = new SecurityConfig(roleName);
        att.add(ca);
        resourceMap.put(url, att);
        return true;
    }
}
