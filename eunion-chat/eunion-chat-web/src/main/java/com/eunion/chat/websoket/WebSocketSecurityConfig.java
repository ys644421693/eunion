package com.eunion.chat.websoket;

import com.eunion.chat.websocket.SystemWebSocketHandler;
import com.eunion.chat.websocket.WebSocketHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by ys on 2016/7/10.
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketSecurityConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        System.out.println("加载websocket 配置");
        registry.addHandler(new SystemWebSocketHandler(),"/echo").addInterceptors(new WebSocketHandshakeInterceptor()).setAllowedOrigins("*"); //支持websocket 的访问链接
        registry.addHandler(new SystemWebSocketHandler(),"/sockjs/echo").addInterceptors(new WebSocketHandshakeInterceptor()).setAllowedOrigins("*").withSockJS(); //不支持websocket的访问链接

    }

}
