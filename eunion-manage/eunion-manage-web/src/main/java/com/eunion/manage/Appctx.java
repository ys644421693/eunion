package com.eunion.manage;

import org.springframework.context.ApplicationContext;

/**
 * Created by ys on 2016/7/3.
 */
public class Appctx {

    public static ApplicationContext ctx=null;

    public static Object getObject(String string){
        return ctx.getBean(string);
    }

    public static void main(String[] args) {
        String url = "/";

        System.out.println(url.matches("(\\/[a-zA-Z0-9]*)+"));
    }

}
