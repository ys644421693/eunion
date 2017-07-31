package com.eunion.manage.serviceimpl.role;

import com.eunion.manage.common.util.PulicClass;
import com.eunion.manage.dto.BaseResponse;
import com.eunion.manage.entity.sysrole.Role;
import com.eunion.manage.entity.sysrole.SystemUrl;
import com.eunion.manage.repository.role.RoleRepository;
import com.eunion.manage.service.role.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Created by ys on 2016/5/6.
 */
@Service("RoleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    public Object addRole(Role role) {
        BaseResponse baseResponse = new BaseResponse();
        Role r = null;
        try{
            r = roleRepository.save(role);
        }catch (ConstraintViolationException e) {
            baseResponse.setMsg(PulicClass.getErrorMessage(e.getMessage()));
            baseResponse.setStatus("000001");
            return baseResponse;
        }
        return  r;
    }

    public List<Role> getAllData() {
        return roleRepository.findAll();
    }

    public void deleteRole(Role role) {
        roleRepository.delete(Long.parseLong(role.getId()+""));
    }

    public List<Role> getRoleBySystemUrls(SystemUrl systemUrl) {
        return roleRepository.getRoleBySystemUrls(systemUrl);
    }

}
