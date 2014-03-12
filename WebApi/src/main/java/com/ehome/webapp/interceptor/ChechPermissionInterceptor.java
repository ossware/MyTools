package com.ehome.webapp.interceptor;

import com.alibaba.fastjson.JSONObject;
import net.sf.cglib.proxy.MethodProxy;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: haoxiaolei
 * Date: 13-7-5
 * Time: 上午9:47
 * To change this template use File | Settings | File Templates.
 * 用户验证是否有权限的拦截器
 */
public class ChechPermissionInterceptor extends BaseInterceptor {

    /**
     * 重写父类 回调方法
     * @param obj
     * @param method
     * @param args HttpServletRequest
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        boolean checkResult = checkPermission(args);
        if (checkResult) return methodProxy.invokeSuper(obj, args);
        else return "{\"error\":\"login fail\",\"msg\":\"请登录！\"}";
    }

    /**
     * 校验用户是否有权限
     * @param args HttpServletRequest
     * @return boolean
     * @throws java.io.IOException
     */
    private boolean checkPermission(Object[] args) throws IOException {
        System.out.println("验证权限...在Api操作之前执行!");
        boolean checkResult = false;

        if (args != null && args.length > 0) {
            HttpServletRequest request = ((HttpServletRequest) args[0]);

            String sessionId = request.getSession().getId();
            String jsonStr = request.getParameterMap().keySet().iterator().next();

            Map jsonMap = JSONObject.parseObject(jsonStr, Map.class);

            String login_key = jsonMap.get("login_key") != null ? jsonMap.get("login_key").toString() : "";
            if(login_key == null || login_key.trim().equals("") || login_key.trim().equals("null") || !sessionId.equals(login_key)) {
                // 说明还未登录，提示用户登录...验证用户名和密码，正确了，则把sessionId写出去
                System.out.println("很抱歉，你还没有登录，不能做任何操作!!!");
                checkResult = false;
            } else {
                System.out.println("页面传过来的sessionId: " + login_key);
                // TODO: 编写已经成功登录之后的逻辑

                checkResult = true;
            }
        }

        return checkResult;
    }

}