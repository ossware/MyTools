package com.iunet.jdk_proxy.service.impl;

import com.iunet.jdk_proxy.service.LoginService;

/**
 * Created with IntelliJ IDEA.
 * User: haoxiaolei
 * Date: 13-7-3
 * Time: 下午4:23
 * To change this template use File | Settings | File Templates.
 */
public class LoginServiceImpl implements LoginService {

    public void login() {
        System.out.println("登录...");
    }

    public void logout() {
        System.out.println("退出...");
    }
}
