package com.eunion.manage.dto.response;

import com.eunion.manage.dto.BaseResponse;
import com.eunion.manage.entity.sysrole.SystemUrl;

/**
 * Created by yangshuo on 2016/11/26.
 */
public class SystemUrlRespones extends BaseResponse {

    private SystemUrl systemUrl;

    public SystemUrl getSystemUrl() {
        return systemUrl;
    }

    public void setSystemUrl(SystemUrl systemUrl) {
        this.systemUrl = systemUrl;
    }
}
