package com.ehome.springmvc.biz.impl;

import com.ehome.springmvc.biz.UserBiz;
import com.ehome.springmvc.dao.BaseDAO;
import com.ehome.springmvc.dao.BlogDAO;
import com.ehome.springmvc.dao.UserDAO;
import com.ehome.springmvc.model.Blog;
import com.ehome.springmvc.model.User;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
@Component("userBiz")
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
            }
        } finally {
            session.close();
        }

        return addResult;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        SqlSession session = baseDAO.getSession();
        if (!session.getConfiguration().hasMapper(UserDAO.class)) {
            session.getConfiguration().addMapper(UserDAO.class);
        }
        try {
            UserDAO userDAO = session.getMapper(UserDAO.class);
            userList = userDAO.getAllUsers();
        } finally {
            session.close();
        }
        return userList;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean addBlogs(User user, Blog blog) {
        boolean result = false;
        SqlSession session = baseDAO.getSession();
        Configuration cfg = session.getConfiguration();
        if (!cfg.hasMapper(UserDAO.class)) {
            cfg.addMapper(UserDAO.class);
        }
        if (!cfg.hasMapper(BlogDAO.class)) {
            cfg.addMapper(BlogDAO.class);
        }
        try {
            UserDAO userDAO = session.getMapper(UserDAO.class);
            boolean addUserResult = userDAO.addNewUser(user);
            BlogDAO blogDAO = session.getMapper(BlogDAO.class);
            boolean addBlogResult = blogDAO.addBlog(blog);
            addUserResult = false;
            if (!addUserResult || !addBlogResult) {
                throw new RuntimeException("保存失败...");
            } else {
                result = true;
            }
        } finally {
            session.close();
        }
        return result;
    }


}
