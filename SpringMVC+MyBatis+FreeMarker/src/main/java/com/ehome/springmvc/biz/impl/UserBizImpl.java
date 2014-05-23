package com.ehome.springmvc.biz.impl;

import com.ehome.springmvc.biz.UserBiz;
import com.ehome.springmvc.dao.BaseDAO;
import com.ehome.springmvc.dao.UserDAO;
import com.ehome.springmvc.model.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package com.ehome.springmvc.biz.impl
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 2014/5/23 0023 14:37
 * @Copyright: 2014 ihome.com
 */
public class UserBizImpl implements UserBiz {
    private BaseDAO baseDAO;

    @Resource
    public void setBaseDAO(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }


    public boolean addNewUser(User user) {
        boolean addResult = false;
        SqlSession session = baseDAO.getSession();
        if (!session.getConfiguration().hasMapper(UserDAO.class)) {
            session.getConfiguration().addMapper(UserDAO.class);
        }
        try {
            UserDAO userDAO = session.getMapper(UserDAO.class);
            addResult = userDAO.addNewUser(user);
            if (addResult) {
                session.commit();
                System.out.println(addResult);
            }
        } finally {
            session.close();
        }

        return addResult;
    }
}
