package com.ehome.webapp.listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.ehome.webapp.share.ApiPath;
import com.ehome.webapp.share.StartRun;
import com.ehome.webapp.util.ClassUtil;

public class StartInitListener implements ServletContextListener {
	private static final long serialVersionUID = -8498741507271850476L;
	
	private Map<String, Method> apiMaps = new HashMap<String, Method>();
	private List<Method> startRunMethodList = new ArrayList<Method>();

	public void contextDestroyed(ServletContextEvent arg0) {}

	public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        initApiMap(context);
        context.setAttribute("apiMaps", apiMaps);
        System.out.println("-------------------------------------------------------------------");
        initDataMaps(context);
        context.setAttribute("startRunMethodList", startRunMethodList);
	}

    /**
     * 初始化WebApi
     * @param context
     */
    private void initApiMap(ServletContext context) {
        if (apiMaps.size() <= 0) {
            Set<Class<?>> apiClzs = new HashSet<Class<?>>();
            String classpath = context.getInitParameter("apipath");
            String[] classpathArray = classpath.split(",");
            for (String clzpath : classpathArray) {
                apiClzs.addAll(ClassUtil.getClasses(clzpath));
            }
            // 收集所有api
            for (Class<?> clz : apiClzs) {
                for (Method m : clz.getMethods()) {
                    ApiPath path = m.getAnnotation(ApiPath.class);
                    if (path != null) {
                        apiMaps.put(path.value(), m);
                    }
                }
            }
        }

        // 遍历所有api
        for (Map.Entry mapEntry : apiMaps.entrySet()) {
            String key = mapEntry.getKey().toString();
            Method method = (Method) mapEntry.getValue();
            System.out.println(key + "------> " + method.toString());
        }
        System.out.println("总共 " + apiMaps.size() + " 个api");
    }

    /**
     * 系统启动初始化数据
     * @param context
     */
    private void initDataMaps(ServletContext context) {
        if (startRunMethodList.size() <= 0) {
            Set<Class<?>> startRunClz = new HashSet<Class<?>>();
            String classpath = context.getInitParameter("startRunMethodPath");
            String[] classpathArray = classpath.split(",");
            for (String clzpath : classpathArray) {
                startRunClz.addAll(ClassUtil.getClasses(clzpath));
            }
            // 收集所有系统启动要运行的方法
            for (Class<?> clz : startRunClz) {
                for (Method m : clz.getMethods()) {
                    StartRun startRun = m.getAnnotation(StartRun.class);
                    if (startRun != null) {
                        startRunMethodList.add(m);
                    }
                }
            }
        }

        // 遍历所有系统启动要运行的方法
        try {
            Object obj;
            for (Method method : startRunMethodList) {
                obj = method.getDeclaringClass().newInstance();
                method.invoke(obj);
                System.out.println("方法----> " + method.getName() + " 已运行完毕!");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println("总共 " + startRunMethodList.size() + " 个系统启动要运行的方法");
    }
}
