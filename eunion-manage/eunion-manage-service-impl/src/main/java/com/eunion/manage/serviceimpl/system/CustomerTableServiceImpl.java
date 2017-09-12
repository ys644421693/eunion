package com.eunion.manage.serviceimpl.system;

import com.eunion.manage.entity.system.TableServiceInfo;
import com.eunion.manage.repository.system.CustomerTableRepository;
import com.eunion.manage.service.system.CustomerTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerTableServiceImpl implements CustomerTableService {

    @Resource
    private CustomerTableRepository customerTableRepository;

    @Override
    public Object saveListData(TableServiceInfo tableServiceInfo) {
        return customerTableRepository.save(tableServiceInfo);
    }

    @Override
    public Object getAllData() {
        return customerTableRepository.findAll();
    }
}
