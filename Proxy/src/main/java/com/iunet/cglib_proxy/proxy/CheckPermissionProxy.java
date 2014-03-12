package com.iunet.cglib_proxy.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: haoxiaolei
 * Date: 13-7-4
 * Time: 下午6:21
 * To change this template use File | Settings | File Templates.
 * 使用cglib 动态代理
 */
public class CheckPermissionProxy implements MethodInterceptor {
    private Object target;

    /**
     * 创建代理对象
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

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
        checkPermission();
        methodProxy.invokeSuper(obj, args);
        return null;
    }

    private void checkPermission() {
        System.out.println("验证权限...在XXXX操作之前执行!");
    }


}
