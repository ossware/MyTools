package com.iunet.jdk_proxy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: haoxiaolei
 * Date: 13-7-3
 * Time: 下午4:25
 * To change this template use File | Settings | File Templates.
 */
public class CheckPermissionProxy implements InvocationHandler {
    private Object target;      // 被代理的对象

    public void setTarget(Object target) {
        this.target = target;
    }

    private void checkPermission() {
        System.out.println("验证权限...在XXXX操作之前执行!");
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        checkPermission();
        method.invoke(target, args);
        return null;
    }
}
