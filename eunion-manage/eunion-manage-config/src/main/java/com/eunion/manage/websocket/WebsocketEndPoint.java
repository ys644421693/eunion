package com.eunion.manage.websocket;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


/**
 * Created by yangshuo on 2016/12/11.
 */
public class WebsocketEndPoint extends TextWebSocketHandler {

    private static Logger logger = org.apache.log4j.Logger.getLogger(WebsocketEndPoint.class);
    @Autowired
    private TelSocketSessionUtils telSocketSessionUtils;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String inquiryId = String.valueOf(session.getAttributes().get("HTTP.SESSION.ID"));
        int empNo = Integer.parseInt(String.valueOf(session.getId()));
        telSocketSessionUtils.add(inquiryId, empNo, session);
    }

    /**
     * 收到客户端消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String inquiryId = String.valueOf(session.getAttributes().get("HTTP.SESSION.ID"));
        int empNo = Integer.parseInt(String.valueOf(session.getId()));
        telSocketSessionUtils.sendMessage(inquiryId, empNo, "【来自服务器的复读机】：" + message.getPayload().toString());
    }

    /**
     * 出现异常
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        String inquiryId = String.valueOf(session.getAttributes().get("HTTP.SESSION.ID"));
        int empNo = Integer.parseInt(String.valueOf(session.getId()));
        logger.error("webSocket connection exception: " + telSocketSessionUtils.getKey(inquiryId, empNo));
        logger.error(exception.getMessage(), exception);

        telSocketSessionUtils.remove(inquiryId, empNo);
    }

    /**
     * 连接关闭
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String inquiryId = String.valueOf(session.getAttributes().get("HTTP.SESSION.ID"));
        int empNo = Integer.parseInt(String.valueOf(session.getId()));
        telSocketSessionUtils.remove(inquiryId, empNo);
    }

    /**
     * 是否分段发送消息
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
