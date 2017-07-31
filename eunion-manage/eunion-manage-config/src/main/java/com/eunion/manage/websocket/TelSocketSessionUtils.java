package com.eunion.manage.websocket;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangshuo on 2016/12/11.
 */
@Service
public class TelSocketSessionUtils {

    private Logger logger = Logger.getLogger(TelSocketSessionUtils.class);

    private Map<String, WebSocketSession> clients = new ConcurrentHashMap<>();
    private Map<String,byte[]> redisSession =  new HashedMap();

    /**
     * 保存一个连接
     *
     * @param inquiryId
     * @param empNo
     * @param session
     */
    @CachePut(value = "webSocketSession", key = "#inquiryId + '_' + #empNo")
    public Map<String,byte[]> add(String inquiryId, int empNo, WebSocketSession session) {
        clients.put(getKey(inquiryId, empNo), session);
        redisSession.put(getKey(inquiryId, empNo), SerializeRedis.serialize(session));
        return redisSession;
    }

    /**
     * 获取一个连接
     *
     * @param inquiryId
     * @param empNo
     * @return
     */
    @Cacheable(value = "webSocketSession", key = "#inquiryId + '_' + #empNo")
    public Map<String,byte[]> get(String inquiryId, int empNo) {
        return redisSession;
    }

    /**
     * 移除一个连接
     *
     * @param inquiryId
     * @param empNo
     */
    @CacheEvict(value = "webSocketSession", key = "#inquiryId + '_' + #empNo")
    public Map<String,byte[]> remove(String inquiryId, int empNo) throws IOException {
        clients.remove(getKey(inquiryId, empNo));
        redisSession.remove(get(inquiryId, empNo));
        return redisSession;
    }

    /**
     * 组装sessionId
     *
     * @param inquiryId
     * @param empNo
     * @return
     */
    public String getKey(String inquiryId, int empNo) {
        return inquiryId + "_" + empNo;
    }

    public WebSocketSession getWebSocketSession(String inquiryId, int empNo) {
        Map<String,byte[]> data =  get(inquiryId,empNo);
        return (WebSocketSession) SerializeRedis.unserialize(data.get(getKey(inquiryId,empNo)));
    }

    /**
     * 判断是否有效连接
     * 判断是否存在
     * 判断连接是否开启
     * 无效的进行清除
     *
     * @param inquiryId
     * @param empNo
     * @return
     */
    public boolean hasConnection(String inquiryId, int empNo) {
        String key = getKey(inquiryId, empNo);
        if (clients.containsKey(key)) {
            return true;
        }

        return false;
    }

    /**
     * 获取连接数的数量
     *
     * @return
     */
    public int getSize() {
        return clients.size();
    }

    /**
     * 发送消息到客户端
     *
     * @param inquiryId
     * @param empNo
     * @param message
     * @throws Exception
     */
    public void sendMessage(String inquiryId, int empNo, String message) throws Exception {
        if (!hasConnection(inquiryId, empNo)) {
            throw new NullPointerException(getKey(inquiryId, empNo) + " connection does not exist");
        }

        WebSocketSession session = getWebSocketSession(inquiryId, empNo);
        try {
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            logger.error("websocket sendMessage exception: " + getKey(inquiryId, empNo));
            logger.error(e.getMessage(), e);
            clients.remove(getKey(inquiryId, empNo));
        }
    }
}
