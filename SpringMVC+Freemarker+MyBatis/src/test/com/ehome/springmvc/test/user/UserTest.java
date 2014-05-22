package com.ehome.springmvc.test.user;

import com.ehome.springmvc.dao.UserDAO;
import com.ehome.springmvc.model.User;
import com.ehome.springmvc.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
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
public class UserTest {
    SqlSession session = MyBatisUtil.getSqlSession();

    @Test
    public void addNewUser() {
        User user = new User();
        user.setUserName("单车上的理想");
        user.setUserAge(28);
        user.setUserAddress("郑州市");
        UserDAO userDAO = session.getMapper(UserDAO.class);
        boolean addResult = userDAO.addNewUser(user);
        System.out.println(addResult);
    }
}
