package com.app.base.common;

import com.app.base.controller.UserController;
import com.app.base.dto.ServiceSessionInfo;
import com.app.base.service.ServiceInfoCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BF100365 on 2016/12/15.
 */
@Component
public class HeadHandler extends AbstractWebSocketHandler {

    @Autowired
    private ServiceInfoCache sessionInfoCache;

    public static final Map<String, WebSocketSession> socketSessionMap = new HashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        //接收到客户端消息时调用
        System.out.println("text message: " + session.getId() + "-" + message.getPayload());

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 与客户端完成连接后调用
        socketSessionMap.put(session.getId(), session);
        if (UserController.sessionId != null ) {
            ServiceSessionInfo serviceSessionInfo = sessionInfoCache.getWebSocketSessionInfo(UserController.sessionId).get(0);
            serviceSessionInfo.setSocketId(session.getId());
            sessionInfoCache.put(serviceSessionInfo);
        }

        session.sendMessage(new TextMessage("你好".getBytes()));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 消息传输出错时调用
        System.out.println("handleTransportError");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // 一个客户端连接断开时关闭
        sessionInfoCache.remove(session.getId());
        socketSessionMap.remove(session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        // TODO Auto-generated method stub
        return false;
    }

    public static void sendAllMessage(String content) throws IOException {
        for (Map.Entry entry : socketSessionMap.entrySet()) {
            WebSocketSession webSocketSession = (WebSocketSession) entry.getValue();
            webSocketSession.sendMessage(new TextMessage(content.getBytes()));
        }
    }

    public static void sendUserMessage(String content, String id) throws IOException {
        for (Map.Entry entry : socketSessionMap.entrySet()) {
            if (id.equals(entry.getKey())) {
                WebSocketSession webSocketSession = (WebSocketSession) entry.getValue();
                webSocketSession.sendMessage(new TextMessage(content.getBytes()));
                break;
            }
        }
    }

    public static WebSocketSession getSessionByAddress(String address) {
        return socketSessionMap.get(address);
    }
}
