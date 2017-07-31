package com.eunion.portals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by ys on 2016/9/3.
 */
//@SpringBootApplication
//@EnableScheduling
public class PortalsEUnion {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        SpringApplication app=new SpringApplication(PortalsEUnion.class);
        Appctx.ctx=app.run(args);
    }
}