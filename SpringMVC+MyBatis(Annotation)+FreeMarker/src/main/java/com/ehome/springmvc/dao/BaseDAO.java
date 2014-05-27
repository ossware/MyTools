package com.ehome.springmvc.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package com.ehome.springmvc.dao
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 2014/5/23 0023 11:53
 * @Copyright: 2014 ihome.com
 */
@Component("baseDAO")
public class BaseDAO {

    private SqlSessionFactory sqlSessionFactory;

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }
}
