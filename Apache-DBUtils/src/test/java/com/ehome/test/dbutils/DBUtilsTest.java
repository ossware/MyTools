package com.ehome.test.dbutils;

import com.ehome.dbutils.bean.NewsBean;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package PACKAGE_NAME
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 14-3-16 上午10:08
 * @Copyright: 2014 ihome.com
 */
public class DBUtilsTest {
    Connection conn = null;
    String jdbcURL = "jdbc:mysql://192.168.1.32:3306/sinagame";
    String jdbcDriver = "com.mysql.jdbc.Driver";

    QueryRunner query = null;

    @Before
    public void init() {
        DbUtils.loadDriver(jdbcDriver);
        try {
            conn = DriverManager.getConnection(jdbcURL, "php", "123456");
            query = new QueryRunner();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void destroy() {
        if (conn != null) {
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * 将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值
     */
    @Test
    public void getDataByMapHandler() {
        try {
            Map<String, Object> map = query.query(conn, "select * from news t where t.id=?", new MapHandler(), new Object[] { 439244 });
            System.out.println(map.get("id") + "--------------" + map.get("title"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将结果集中的每一行数据都封装到一个Map里，然后再存放到List
     */
    @Test
    public void getDataByMapListHandler() {
        try {
            List<Map<String, Object>> listMap = query.query(conn, "select * from news t", new MapListHandler());
            for (Map<String, Object> map : listMap) {
                System.out.println(map.get("id") + "--------------" + map.get("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将结果集中的第一行数据封装到一个对应的JavaBean实例中
     */
    @Test
    public void getDataByBeanHandler() {
        try {
            NewsBean newsBean = (NewsBean) query.query(conn, "select * from news t where t.id = ?", new BeanHandler(NewsBean.class), new Object[] { 439263 });
            System.out.println(newsBean.getId() + "--------------" + newsBean.getTitle());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getDataByBeanListHandler() {
        try {
            List<NewsBean> newsBeanList = (List<NewsBean>) query.query(conn, "select * from news t", new BeanListHandler(NewsBean.class));
            for (NewsBean newsBean : newsBeanList) {
                System.out.println(newsBean.getId() + "--------------" + newsBean.getTitle() + "-----------" + newsBean.getGalleryId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把结果集中的第一行数据转成对象数组
     */
    @Test
    public void getDataByArrayHandler() {
        try {
            Object[] objArr = query.query(conn, "select * from news t where t.id = ?", new ArrayHandler(), new Object[] { 439263 });
            System.out.println(objArr[0] + "--------------" + objArr[3]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把结果集中的每一行数据都转成一个对象数组，再存放到List中
     */
    @Test
    public void getDataByArrayListHandler() {
        try {
            List<Object[]> list = query.query(conn, "select * from news t", new ArrayListHandler());
            for (Object[] objArr : list) {
                System.out.println(objArr[0] + "-------------" + objArr[3]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将结果集中某一列的数据存放到List中
     */
    @Test
    public void getDataByColumnListHandler() {
        try {
            List<Object> list = (List<Object>) query.query(conn, "select * from news t", new ColumnListHandler("title"));
            for (Object obj : list) {
                System.out.println(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将结果集中某一条记录的其中某一列的数据存成Object
     * 获取某一列的值
     */
    @Test
    public void getDataByScalarHandler() {
        try {
            String title = (String) query.query(conn, "select * from news t where t.id = ?", new ScalarHandler("title"), new Object[] { 439263 });
            System.out.println(title);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertData() {
        try {
            query.update(conn, "insert into news(title,description) values(?,?)", new Object[]{"jdbc插入的...","新闻描述..."});
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DbUtils.rollback(conn);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void deleteData() {
        try {
            query.update(conn, "delete t from news t where t.id = ?", new Object[] { 439563 });
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DbUtils.rollback(conn);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void updateData() {
        try {
            query.update(conn, "update news t set t.title = ? where t.id = ?", new Object[] { "modifyed title", 439563 });
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DbUtils.rollback(conn);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
