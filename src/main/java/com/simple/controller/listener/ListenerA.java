package com.simple.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 实现【生命周期监听器】
 * 可以在监听器中存放一些 tomcat 启动时就要完成的代码
 *
 * @author Jinhua
 */
@WebListener(value = "ListenerA")
public class ListenerA implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("出生之后调用");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("死亡之后调用");
    }
}
