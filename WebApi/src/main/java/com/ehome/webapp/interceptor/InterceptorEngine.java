package com.ehome.webapp.interceptor;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @version V1.0
 * @Project: VWPS
 * @Title:
 * @Package com.ds.vwps.interceptor
 * @Description: 给某个类的某个方法切入拦截方法的引擎
 * @Author xiaolei-0228@163.com
 * @Date 2014-04-03 0003 12:30
 * @Copyright: 2014 ihome.com
 */
public class InterceptorEngine extends BaseInterceptor {
    private Object target;              // 被切入体，即目标对象
    private Object cutObj;              // 切入体
    private Method cutObjMethod;       // 切入体的某个方法
    private Object[] cutObjMethodArgs; // 切入体的某个方法的参数列表

    /**
     * 创建代理对象
     *
     * @param target 被切入体，即目标对象
     *
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
     * 创建代理对象
     *
     * @param target       被切入体，即目标对象
     * @param cutObj       切入体
     * @param cutObjMethod 要执行切入体的方法
     *
     * @return
     */
    public Object getInstance(Object target, Object cutObj, Method cutObjMethod, Object... cutObjMethodArgs) {
        this.target = target;
        this.cutObj = cutObj;
        this.cutObjMethod = cutObjMethod;
        this.cutObjMethodArgs = cutObjMethodArgs;
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
        if (cutObj != null && cutObjMethod != null) {
            cutObjMethod.invoke(cutObj, cutObjMethodArgs);
        }
        return methodProxy.invokeSuper(obj, args);
    }

}
