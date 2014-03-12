package com.ehome.webapp.servlet;

import com.ehome.webapp.interceptor.ChechPermissionInterceptor;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebApiServlet extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 6712913960493752645L;
	Map<String, Method> apiMaps = null;

    private String permission;
    private String excludePermissionApi;    // 排除权限校验的Api

    public void init(ServletConfig config) throws ServletException {
        permission = config.getInitParameter("permission");
        excludePermissionApi = config.getInitParameter("excludePermissionApi");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("请求的api：" + request.getRequestURI());
        apiMaps = (Map<String, Method>) request.getServletContext().getAttribute("apiMaps");
        String apiPath = request.getRequestURI();
        if(apiPath == null || "".equals(apiPath) || "null".equals(apiPath)) {
            // 无效api地址
            response.getWriter().println("无效api地址：" + apiPath);
        } else {
            apiPath = apiPath.contains(".json") ? apiPath.substring(0, apiPath.indexOf(".")) : apiPath;
            apiPath = apiPath.substring(request.getContextPath().length() + 4); // 把命名空间过滤掉
        }

        // 获取并解析页面传过来的json数据
        String formDate = request.getParameterMap().keySet().iterator().next();

        // 从apiMaps里拿到要执行的方法并运行
        Method m = apiMaps.get(apiPath);
        if (m != null) {
            try {
                Object obj = m.getDeclaringClass().newInstance();

                // 加入权限校验(所有请求的方法都会被此拦截器拦截到)
                if (permission != null && permission.equals("true")) {
                    if (!obj.getClass().getName().equals(excludePermissionApi)) {   // 如果不是被排除的Api就执行代理
                        ChechPermissionInterceptor interceptor = new ChechPermissionInterceptor();
                        obj = interceptor.getInstance(obj);
                    }
                }

                Object result = m.invoke(obj, request, formDate);
                response.getWriter().println(result != null ? result.toString() : "");	//返回给页面数据
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }


}
