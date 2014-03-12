package com.iunet.test.webapi;

import com.alibaba.fastjson.JSONObject;
import com.ehome.webapp.share.RequestUrl;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package com.iunet.test
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 14-3-8 下午5:23
 * @Copyright: 2014 ihome.com
 */
public class LoginApiTest {
    HttpRequest request = null;
    JSONObject paramJson;

    @Before
    public void init() {
        request = new HttpRequest();
        paramJson = new JSONObject();
        paramJson.put("language", "zh_CN");
    }

    @Test
    public void login() {
        String url = "http://127.0.0.1:8080/webapi/api" + RequestUrl.LOGIN;
        Map<String, Object> paramMap = new HashMap<String, Object>();     // 需要变的就是map，里面放的就是传过来的参数
        paramMap.put("userName", "admin");
        paramMap.put("pwd", "admin");
        paramJson.put("params", paramMap);  // 这一句最重要
        String paramsStr = JSONObject.toJSONString(paramJson);
        HttpResponse response = HttpRequest.post(url).form(paramsStr, "").send();
        System.out.println("返回的数据：" + response.bodyText());
    }








}
