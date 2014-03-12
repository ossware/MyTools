package com.ehome.webapp.webapi;

import com.alibaba.fastjson.JSONObject;
import com.ehome.webapp.share.ApiPath;
import com.ehome.webapp.share.RequestUrl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: haoxiaolei
 * Date: 13-7-22
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public class LoginApi extends BaseApi {

    @ApiPath(RequestUrl.LOGIN)
    public String login(HttpServletRequest request, String jsonData) throws IOException {
        getParamsObj(jsonData);
        Map jsonMap = JSONObject.parseObject(jsonData, Map.class);
        String userName = jsonMap.get("userName") != null ? jsonMap.get("userName").toString() : "";
        String pwd = jsonMap.get("pwd") != null ? jsonMap.get("pwd").toString() : "";

        if (userName.equals("admin") && pwd.equals("admin")) {
            String login_key = request.getSession().getId();
            return login_key;
        }

        return "";
    }
}
