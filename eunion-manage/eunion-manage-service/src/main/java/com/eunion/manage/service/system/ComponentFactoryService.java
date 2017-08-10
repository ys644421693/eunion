package com.eunion.manage.service.system;

import com.eunion.manage.entity.table.ComponentFactory;

public interface ComponentFactoryService {

    public Object addComponent(ComponentFactory componentFactory);

    Object getAllComponent();

    void deleteComponentById(int id);
}
