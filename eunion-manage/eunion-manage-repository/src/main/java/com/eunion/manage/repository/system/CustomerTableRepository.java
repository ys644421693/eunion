package com.eunion.manage.repository.system;

import com.eunion.manage.entity.system.TableServiceInfo;
import org.springframework.data.repository.CrudRepository;

public interface CustomerTableRepository extends CrudRepository<TableServiceInfo, Long> {
}
