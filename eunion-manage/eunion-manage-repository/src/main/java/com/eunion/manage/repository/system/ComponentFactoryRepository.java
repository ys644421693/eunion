package com.eunion.manage.repository.system;

import com.eunion.manage.entity.table.ComponentFactory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComponentFactoryRepository extends CrudRepository<ComponentFactory,Integer> {

    public List<ComponentFactory> findAll();

    public void deleteById(int id);

}
