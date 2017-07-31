package com.eunion.portals;

import org.springframework.context.ApplicationContext;

/**
 * Created by ys on 2016/7/3.
 */
public class Appctx {

    public static ApplicationContext ctx=null;

    public static Object getObject(String string){
        return ctx.getBean(string);
    }

}
