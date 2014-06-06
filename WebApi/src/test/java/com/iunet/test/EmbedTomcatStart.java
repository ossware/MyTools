package com.iunet.test;

import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;

/**
 * Created with IntelliJ IDEA.
 * User: xiaolei
 * Date: 13-5-11
 * Time: 上午2:04
 * To change this template use File | Settings | File Templates.
 */
public class EmbedTomcatStart {

    //不依赖IDE工具配置
//    private static String webrootPath = "E:\\MyProjects\\MyCode\\CommonTools\\WebApi\\WebRoot";   //WEB应用程序路径
//    private static String contextPath = "/";    //WEB上下文名称

    private static String webrootPath = System.getProperty("webroot.path");   //WEB应用程序路径
    private static String contextPath = System.getProperty("context.path");    //WEB上下文名称


    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        tomcat.setBaseDir(".");
        tomcat.getHost().setAppBase(webrootPath);

        // Add AprLifecycleListener
        StandardServer server = (StandardServer) tomcat.getServer();
        AprLifecycleListener listener = new AprLifecycleListener();
        server.addLifecycleListener(listener);

        tomcat.addWebapp(contextPath, webrootPath);
        tomcat.start();
        System.out.println("Tomcat 7 启动完毕！");
        tomcat.getServer().await();

    }
}
