package com.app.base.service.impl;

import com.app.base.dto.ServiceSessionInfo;
import com.app.base.service.ServiceInfoCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by BF100365 on 2016/12/16.
 */
@Service("sessionInfoCache")
public class ServiceInfoCacheImpl implements ServiceInfoCache {

    private Logger logger = LoggerFactory.getLogger(ServiceInfoCacheImpl.class);

    private static final List<ServiceSessionInfo> webSocketSessionInfoList = new ArrayList<ServiceSessionInfo>();

    @CachePut(value = "service", key = "#info.getSessionId()")
    public List<ServiceSessionInfo> put(ServiceSessionInfo info) {
        int count = webSocketSessionInfoList.size()-1;
        for (; count >= 0; count--) {
            if (info.getSessionId().equals(webSocketSessionInfoList.get(count).getSessionId())) {
                webSocketSessionInfoList.remove(count);
            }
        }
        this.webSocketSessionInfoList.add(info);
        logger.debug("---------------添加数据" + info.getSessionId() + "到缓存------------------");
        return this.webSocketSessionInfoList;
    }

    @Cacheable(value = "serviceInfo", key = "#sessionId")
    public List<ServiceSessionInfo> getWebSocketSessionInfo(String sessionId) {
        logger.debug("---------------从缓存中获取数据------------------");
        List<ServiceSessionInfo> list = new ArrayList<>();
        for (ServiceSessionInfo serviceSessionInfo : webSocketSessionInfoList) {
            if (serviceSessionInfo.getSessionId() == sessionId) {
                list.add(serviceSessionInfo);
                return list;
            }
        }
        return list;
    }

    @CacheEvict(value = "service", allEntries = true)
    public List<ServiceSessionInfo> clear() {
        logger.debug("---------------清空所有数据------------------");
        webSocketSessionInfoList.clear();
        return webSocketSessionInfoList;
    }

    @CacheEvict(value = "service", key = "#sessionId")
    public List<ServiceSessionInfo> remove(String sessionId) {
        logger.debug("---------------删除数据" + sessionId + "------------------");
        int count = webSocketSessionInfoList.size();
        for (int i = 0; i < count; i++) {
            if (webSocketSessionInfoList.get(i).getSessionId().equals(sessionId)) {
                webSocketSessionInfoList.remove(i);
                return webSocketSessionInfoList;
            }
        }
        return webSocketSessionInfoList;
    }

    @Cacheable(value = "service")
    public List<ServiceSessionInfo> getAll() {
        return this.webSocketSessionInfoList;
    }
}
