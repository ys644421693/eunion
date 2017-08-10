package com.eunion.manage.serviceimpl.system;

import com.eunion.manage.entity.table.ComponentFactory;
import com.eunion.manage.repository.system.ComponentFactoryRepository;
import com.eunion.manage.service.system.ComponentFactoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ComponentFactoryImpl implements ComponentFactoryService {

    @Resource
    private ComponentFactoryRepository componentFactoryRepository;

    @Override
    public Object addComponent(ComponentFactory componentFactory) {
        return componentFactoryRepository.save(componentFactory);
    }

    @Override
    public Object getAllComponent() {
        return componentFactoryRepository.findAll();
    }

    @Override
    public void deleteComponentById(int id) {
        componentFactoryRepository.deleteById(id);
    }
}
