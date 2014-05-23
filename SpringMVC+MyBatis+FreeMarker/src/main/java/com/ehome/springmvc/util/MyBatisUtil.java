package com.ehome.springmvc.util;

import com.ehome.springmvc.dao.UserDAO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package com.ehome.springmvc.util
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 2014/5/23 0023 10:35
 * @Copyright: 2014 ihome.com
 */
public class MyBatisUtil {
    private static SqlSessionFactory factory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(reader);
            // 由于使用了注解，所以在主配置文件没有mapper，需要在代码里显示注册该mapper接口
            factory.getConfiguration().addMapper(UserDAO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getFactory() {
        return factory;
    }

    public static SqlSession openSession() {
        return factory.openSession();
    }

    public static void closeReader() {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
