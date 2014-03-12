package com.ehome.webapp.webapi;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.ehome.webapp.biz.UserBiz;
import com.ehome.webapp.interceptor.SaveDataInterceptor;
import com.ehome.webapp.share.ApiPath;
import com.ehome.webapp.share.RequestUrl;

public class UserApi {
    private SaveDataInterceptor saveDataInterceptor = new SaveDataInterceptor();

	@ApiPath(RequestUrl.USER_ADD_USER)
	public String addUser(HttpServletRequest request) {
        try {
            List<Part> list = (List<Part>) request.getParts();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }


        Map map = request.getParameterMap();
		String userJson = (map != null || map.size() == 0) ? ((String) map.keySet().iterator(). next()) : "";	// 这里就是一个json格式的对象,可以转成java对象
		System.out.println("addUser : " + userJson);

        // 使用代理拦截器
        UserBiz userBiz = (UserBiz) saveDataInterceptor.getInstance(new UserBiz());
        userBiz.addUser();

		return "我是返回值";
	}

	@ApiPath(RequestUrl.USER_DELETE_USER)
	public void deleteUser(HttpServletRequest request) {
		System.out.println("delete user......" + request.getParameter("user_json"));
	}
}
