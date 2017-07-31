package com.eunion.manage.service.role;


import com.eunion.manage.dto.request.UrlRoleDtoRequest;
import com.eunion.manage.entity.sysrole.SystemUrl;

import java.util.List;

/**
 * Created by ys on 2016/5/8.
 */
public interface UrlService {

    Object addUrl(SystemUrl url);

    List<SystemUrl> getAllData();

    void deleteUrl(SystemUrl url);

    Object addUrlRole(UrlRoleDtoRequest urlRoleDtoRequest);

    Object deleteUrlRole(UrlRoleDtoRequest urlRoleDtoRequest);

    Object updateUrl(SystemUrl url);

}
