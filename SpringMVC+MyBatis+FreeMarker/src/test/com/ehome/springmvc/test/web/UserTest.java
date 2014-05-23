package com.ehome.springmvc.test.web;

import com.ehome.springmvc.model.User;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.junit.Test;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package PACKAGE_NAME
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 2014/5/22 0022 23:48
 * @Copyright: 2014 ihome.com
 */
public class UserTest extends BaseWebRequestTest {

    @Test
    public void addNewUser() {
        url += "/addUser";
        User user = new User();
        user.setUserName("单车上的理想");
        user.setUserAge(28);
        user.setUserAddress("郑州市");
        HttpResponse response = HttpRequest.post(url).send();
        System.out.println("返回的数据：" + response.bodyText());
    }
}
