package com.xiaolei.webapp.hook;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: xiaolei
 * Date: 13-8-5
 * Time: 下午9:15
 * To change this template use File | Settings | File Templates.
 */
public class ShutdownHook extends Thread {
    private Logger logger = Logger.getLogger(ShutdownHook.class.getName());

    public void run() {
        logger.info("hook记录日志--------------------------");
        System. out.println("hook执行一些操作......=================================" );
    }
}
