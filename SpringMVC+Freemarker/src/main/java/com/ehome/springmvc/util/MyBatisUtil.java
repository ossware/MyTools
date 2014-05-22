package com.ehome.springmvc.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package PACKAGE_NAME
 * @Description: MyBatis工具类
 * @Author xiaolei-0228@163.com
 * @Date 2014/5/22 0022 16:06
 * @Copyright: 2014 ihome.com
 */
public class MyBatisUtil {
    private static String MYBATIS_CONFIG_XML = "mybatis-config.xml";
    private static SqlSessionFactory sessionFactory = null;
    private static InputStream is = null;

    static {
        try {
            is = Resources.getResourceAsStream(MYBATIS_CONFIG_XML);
            sessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static SqlSessionFactory getSession() throws IOException {
        return sessionFactory;
    }
}
