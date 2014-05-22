package com.ehome.springmvc.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package PACKAGE_NAME
 * @Author xiaolei-0228@163.com
 * @Date 2014/5/22 0022 16:06
 * @Copyright: 2014 ihome.com
 */
public class BaseDAO {
    private SqlSessionFactory sqlSession;

    public void setSqlSession(SqlSessionFactory sqlSession) {
        this.sqlSession = sqlSession;
    }

    public SqlSessionFactory getSqlSession() {
        return sqlSession;
    }

}
