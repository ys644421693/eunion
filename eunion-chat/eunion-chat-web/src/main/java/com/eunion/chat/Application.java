package com.eunion.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by ys on 2016/7/10.
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
            SpringApplication app=new SpringApplication(Application.class);
            Appctx.ctx=app.run(args);
    }
}
