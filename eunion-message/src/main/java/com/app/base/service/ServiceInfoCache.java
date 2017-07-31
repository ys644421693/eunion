package com.app.base.service;

import com.app.base.dto.ServiceSessionInfo;

import java.util.List;

/**
 * Created by BF100365 on 2016/12/16.
 */
public interface ServiceInfoCache {

    List<ServiceSessionInfo> put(ServiceSessionInfo info);

    List<ServiceSessionInfo> getWebSocketSessionInfo(String sessionId);

    List<ServiceSessionInfo>  clear();

    List<ServiceSessionInfo>  remove(String sessionId);

    List<ServiceSessionInfo> getAll();
}
