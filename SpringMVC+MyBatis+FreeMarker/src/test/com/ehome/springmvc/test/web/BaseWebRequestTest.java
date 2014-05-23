package com.ehome.springmvc.test.web;

import com.alibaba.fastjson.JSONObject;
import jodd.http.HttpRequest;
import org.junit.Before;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package com.ehome.springmvc.test.user
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 2014/5/23 0023 16:04
 * @Copyright: 2014 ihome.com
 */
public class BaseWebRequestTest {
    protected String url = "http://127.0.0.1/mvc";
    protected HttpRequest request = null;
    protected JSONObject paramJson;
    @Before
    public void init() {
        request = new HttpRequest();
        paramJson = new JSONObject();
        paramJson.put("language", "zh_CN");
    }
}
