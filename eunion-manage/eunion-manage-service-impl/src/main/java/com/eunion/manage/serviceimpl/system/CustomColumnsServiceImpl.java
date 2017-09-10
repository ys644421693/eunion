package com.eunion.manage.serviceimpl.system;

import com.eunion.manage.entity.system.CustomColumns;
import com.eunion.manage.repository.system.CustomColumnsRepository;
import com.eunion.manage.service.system.CustomColumnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomColumnsServiceImpl implements CustomColumnsService {

    @Autowired
    private CustomColumnsRepository customColumnsRepository;

    @Override
    public Object save(List<CustomColumns> customColumns) {
        return customColumnsRepository.save(customColumns);
    }
}
