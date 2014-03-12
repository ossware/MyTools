package com.xiaolei.webapp.interceptor;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: haoxiaolei
 * Date: 13-7-22
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
public class SaveDataInterceptor extends BaseInterceptor {

    /**
     * 回调方法
     * @param obj
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        dbProcessBefore(args);
        methodProxy.invokeSuper(obj, args);
        dbProcessAfter(args);
        return null;
    }


    public void dbProcessBefore(Object[] args) {
        System.out.println("dbProcessBefore......");
    }

    public void dbProcessAfter(Object[] args) {
        System.out.println("dbProcessAfter......");
    }

}
