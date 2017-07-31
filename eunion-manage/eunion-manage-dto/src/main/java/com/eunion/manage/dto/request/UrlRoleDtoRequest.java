package com.eunion.manage.dto.request;

import com.eunion.manage.entity.sysrole.SystemUrl;

/**
 * Created by yangshuo on 2016/11/6.
 */
public class UrlRoleDtoRequest extends SystemUrl {
    private long urlId;

    public long getUrlId() {
        return urlId;
    }

    public void setUrlId(long urlId) {
        this.urlId = urlId;
    }
}
