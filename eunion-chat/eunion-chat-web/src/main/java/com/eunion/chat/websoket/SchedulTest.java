package com.eunion.chat.websoket;

import com.eunion.chat.websocket.SystemWebSocketHandler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

/**
 * Created by ys on 2016/7/13.
 */
@Component
public class SchedulTest {

    @Scheduled(cron = "0/5 * * * * *")
    public void testYY(){
        SystemWebSocketHandler systemWebSocketHandler= new SystemWebSocketHandler();
        systemWebSocketHandler.sendMessageToUsers(new TextMessage("231321365465435"));
    }
}
