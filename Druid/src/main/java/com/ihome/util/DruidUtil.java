package com.ihome.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package PACKAGE_NAME
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 14-2-7 下午5:04
 * @Copyright: 2014 ihome.com
 */
public class DruidUtil {

    private static DruidDataSource dataSource;

    private static Properties properties;

    /**
     * 初始化数据库访问参数
     */
    private static void init() {
        properties = loadPropertyFile("db.properties");
        try {
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        init();
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() throws SQLException {
        if (dataSource != null) return dataSource.getConnection();

        return null;
    }

    /**
     * 读取数据库配置文件
     * @param fullFile
     * @return
     */
    private static Properties loadPropertyFile(String fullFile) {
        String webRootPath = null;
        if (null == fullFile || fullFile.equals(""))
            throw new IllegalArgumentException("Properties file path can not be null : " + fullFile);
        webRootPath = DruidUtil.class.getClassLoader().getResource("").getPath();
        InputStream inputStream = null;
        Properties p = null;
        try {
            inputStream = new FileInputStream(new File(webRootPath + File.separator + fullFile));
            p = new Properties();
            p.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Properties file not found: " + fullFile);
        } catch (IOException e) {
            throw new IllegalArgumentException("Properties file can not be loading: " + fullFile);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return p;
    }

}
