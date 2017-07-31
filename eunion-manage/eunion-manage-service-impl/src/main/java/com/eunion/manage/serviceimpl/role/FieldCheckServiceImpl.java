package com.eunion.manage.serviceimpl.role;

import com.eunion.manage.entity.sysrole.FieldCheck;
import com.eunion.manage.repository.role.FieldCheckRepository;
import com.eunion.manage.service.role.FieldCheckService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yangshuo on 2016/11/24.
 */
@Service
public class FieldCheckServiceImpl implements FieldCheckService {

    @Resource
    private FieldCheckRepository fieldCheckRepository;

    @Override
    public List<FieldCheck> getAllFieldCheck() {
        return (List<FieldCheck>) fieldCheckRepository.findAll();
    }

    @Override
    public FieldCheck saveFieldCheck(FieldCheck fieldCheck) {
        return (FieldCheck) fieldCheckRepository.save(fieldCheck);
    }

    @Override
    public void deleteFieldCheck(FieldCheck fieldCheck) {
        fieldCheckRepository.delete(fieldCheck);
    }
}
