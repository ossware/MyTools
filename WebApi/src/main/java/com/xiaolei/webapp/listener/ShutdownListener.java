package com.xiaolei.webapp.listener;

import com.xiaolei.webapp.hook.ShutdownHook;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * User: xiaolei
 * Date: 13-8-5
 * Time: 下午9:12
 * To change this template use File | Settings | File Templates.
 * 在容器关闭时候要监听的事件
 */
public class ShutdownListener implements ServletContextListener {

    /**
     * 初始化
     */
    public void contextInitialized(ServletContextEvent sce) {
        ShutdownHook shutdownHook = new ShutdownHook();
        Runtime. getRuntime().addShutdownHook(shutdownHook);      // 注册一个hook线程
    }


    /**
     * 销毁
     */
    public void contextDestroyed(ServletContextEvent sce) {
        System. err.println("执行销毁工作开始......" );

        Runtime. getRuntime().runFinalization();     // 启动在初始化时候注册的线程
    }


}
