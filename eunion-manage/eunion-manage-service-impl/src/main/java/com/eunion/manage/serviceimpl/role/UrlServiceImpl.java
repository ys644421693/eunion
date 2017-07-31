package com.eunion.manage.serviceimpl.role;

import com.eunion.manage.common.util.PulicClass;
import com.eunion.manage.dto.BaseResponse;
import com.eunion.manage.dto.request.UrlRoleDtoRequest;
import com.eunion.manage.dto.response.SystemUrlRespones;
import com.eunion.manage.entity.sysrole.CheckInfo;
import com.eunion.manage.entity.sysrole.FieldCheck;
import com.eunion.manage.entity.sysrole.Role;
import com.eunion.manage.entity.sysrole.SystemUrl;
import com.eunion.manage.repository.role.FieldCheckRepository;
import com.eunion.manage.repository.role.RoleRepository;
import com.eunion.manage.repository.role.SystemUrlRepository;
import com.eunion.manage.security.SystemInvocationSecurityMetadataSource;
import com.eunion.manage.service.role.UrlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by ys on 2016/5/8.
 */
@Service("urlService")
public class UrlServiceImpl implements UrlService {

    @Resource
    private SystemUrlRepository systemUrlRepository;

    @Resource
    private RoleRepository roleRepository;

    private SystemUrl systemUrl;

    public Object addUrl(SystemUrl url) {
        BaseResponse baseResponse = new BaseResponse();
        SystemUrl systemUrl = null;
        try {
            systemUrl = systemUrlRepository.save(url);
            SystemInvocationSecurityMetadataSource.addUrlRole("SECURITY_ROLE", systemUrl.getUrl());
        } catch (ConstraintViolationException e) {
            baseResponse.setMsg(PulicClass.getErrorMessage(e.getMessage()));
            baseResponse.setStatus("000001");
            return baseResponse;
        }
        return systemUrl;
    }

    @Override
    public Object updateUrl(SystemUrl url) {
        SystemUrlRespones baseResponse = new SystemUrlRespones();
        try {

            SystemUrl systemUrl = systemUrlRepository.findById(url.getId());
            systemUrl.setUrlName(url.getUrlName());
            systemUrl.setUrl(url.getUrl());
            systemUrl.setDescript(url.getDescript());
            systemUrl.setFieldCheck(url.getFieldCheck());
            baseResponse.setSystemUrl(systemUrlRepository.save(systemUrl));
            baseResponse.setMsg("操作成功");
            baseResponse.setStatus("000000");
        } catch (ConstraintViolationException e) {
            baseResponse.setSystemUrl(systemUrlRepository.save(systemUrl));
            baseResponse.setMsg(PulicClass.getErrorMessage(e.getMessage()));
            baseResponse.setStatus("000001");
        }
        return baseResponse;
    }

    @Override
    public List<SystemUrl> getAllData() {
        return systemUrlRepository.findAll();
    }

    @Override
    public void deleteUrl(SystemUrl url) {
        systemUrlRepository.delete(Long.parseLong(url.getId() + ""));
    }

    @Override
    public Object addUrlRole(UrlRoleDtoRequest urlRoleDtoRequest) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            SystemUrl systemUrl = systemUrlRepository.findById(urlRoleDtoRequest.getUrlId());
            Role role = roleRepository.findById(urlRoleDtoRequest.getId());
            systemUrl.getRoles().add(role);
            systemUrlRepository.save(systemUrl);
            SystemInvocationSecurityMetadataSource.addUrlRole(role.getRoleName(), systemUrl.getUrl());
            baseResponse.setMsg("添加成功");
            baseResponse.setStatus("000000");
        } catch (ConstraintViolationException e) {
            baseResponse.setMsg(PulicClass.getErrorMessage(e.getMessage()));
            baseResponse.setStatus("000001");
        }
        return baseResponse;
    }

    @Override
    public Object deleteUrlRole(UrlRoleDtoRequest urlRoleDtoRequest) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            SystemUrl systemUrl = systemUrlRepository.findById(urlRoleDtoRequest.getUrlId());
            Set<Role> roles = systemUrl.getRoles();
            Role temp = null;
            for (Role role : roles) {
                if (role.getId() == urlRoleDtoRequest.getId()) {
                    temp = role;
                    roles.remove(role);
                    break;
                }
            }
            systemUrlRepository.save(systemUrl);
            SystemInvocationSecurityMetadataSource.removeUrlRole(temp.getRoleName(), systemUrl.getUrl());
            baseResponse.setMsg("添加成功");
            baseResponse.setStatus("000000");
        } catch (ConstraintViolationException e) {
            baseResponse.setMsg(PulicClass.getErrorMessage(e.getMessage()));
            baseResponse.setStatus("000001");
        }
        return baseResponse;
    }

}
