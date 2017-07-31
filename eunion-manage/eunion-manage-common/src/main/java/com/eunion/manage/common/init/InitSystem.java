package com.eunion.manage.common.init;

import org.apache.commons.io.FileUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by ys on 2016/4/27.
 */
public class InitSystem implements ServletContextListener {
    private static final String SQL_INIT_FILE = "SQLInitConfig";

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String logbackConfigLocation = servletContextEvent.getServletContext().getInitParameter(SQL_INIT_FILE);

        if(logbackConfigLocation==null){
            System.out.println("------------无初始化sql配置--------");
            return ;
        }else if("false".equals(logbackConfigLocation)){
            System.out.println("----------禁止初始化--------");
            return ;
        }
        WebApplicationContext applicationContext =  WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        JdbcTemplate jdbcTemplate = (JdbcTemplate)applicationContext.getBean("jdbcTemplate");
        String fn = servletContextEvent.getServletContext().getRealPath(logbackConfigLocation);

        File file = new File(fn);
        try {
            String sqls = FileUtils.readFileToString(file,"UTF-8").replace("\n","");
            String[] sqlStrs = sqls.split(";");
            for(String sql : sqlStrs){
                System.out.println(sql);
                if(sql.toLowerCase().indexOf("create") < 10){
                    jdbcTemplate.execute(sql);
                }else if(sql.toLowerCase().indexOf("drop") < 10){

                }else if(sql.toLowerCase().indexOf("insert") < 10){
                    jdbcTemplate.update(sql);
                }else if(sql.toLowerCase().indexOf("update") < 10){
                    jdbcTemplate.update(sql);
                }else if(sql.toLowerCase().indexOf("delete") < 10){
                    jdbcTemplate.update(sql);
                }else if(sql.toLowerCase().indexOf("select") < 10){

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
