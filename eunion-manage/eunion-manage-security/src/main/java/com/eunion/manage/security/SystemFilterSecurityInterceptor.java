package com.eunion.manage.security;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import java.io.IOException;

/**
 * 最核心的代码就是invoke方法中的InterceptorStatusToken token = super.beforeInvocation(fi);这一句，
 * 即在执行doFilter之前，进行权限的检查，而具体的实现已经交给accessDecisionManager了
 * Created by ys on 2016/4/17.
 */
public class SystemFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    private SystemInvocationSecurityMetadataSource securityMetadataSource;

    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation filterInvocation = new FilterInvocation(servletRequest,servletResponse,filterChain);
        invoke(filterInvocation);
    }

    public void destroy() {

    }

    public void invoke(FilterInvocation fi) throws IOException,ServletException {
        InterceptorStatusToken token= super .beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super .afterInvocation(token, null );
        }
    }

    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    public void setSecurityMetadataSource(SystemInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

}
