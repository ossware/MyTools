package com.xiaolei.webapp.listener;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xiaolei.webapp.share.ApiPath;
import com.xiaolei.webapp.util.ClassUtil;

public class InitApiListener implements ServletContextListener {
	private static final long serialVersionUID = -8498741507271850476L;
	
	private Map<String, Method> apiMaps = new HashMap<String, Method>();
	
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent event) {
		if (apiMaps.size() <= 0) {
			Set<Class<?>> apiClzs = new HashSet<Class<?>>();
			String classpath = event.getServletContext().getInitParameter("apipath");
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
		System.out.println("总共 " + apiMaps.size() + " 个api");
        // 遍历所有api
        for (Map.Entry mapEntry : apiMaps.entrySet()) {
            String key = mapEntry.getKey().toString();
            Method method = (Method) mapEntry.getValue();
            System.out.println(key + "------> " + method.toString());
        }
		event.getServletContext().setAttribute("apiMaps", apiMaps);
	}

}
