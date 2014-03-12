/**----------------------------------------------------------------
 // Copyright (C) 2013 北京品众互动网络营销技术有限公司版权所有。
 // @project: MyTools
 // @package: com.ihome.sharding.config
 // @className: DBUtil
 //
 // @author: haoxiaolei
 // @date： 13-10-26 下午5:58
 // @version: v1.0
 //----------------------------------------------------------------*/
package com.ihome.sharding.config;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title: 数据库操作类
 * @Package com.ihome.sharding.config
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 13-10-26 下午5:58
 * @Copyright: 2013 ihome.com
 */
public class DBUtil {

    private static Connection conn = null;
    private static QueryRunner qr = new QueryRunner();

    /**
     * 获取数据库连接
     *
     * @param database
     *
     * @return
     */
    public static Connection getConnection(Database database) {
        DbUtils.loadDriver(database.getDriver());
        try {
            conn = DriverManager.getConnection(database.getJDBCUrl(), database.getUid(), database.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * 关闭当前数据库连接
     */
    private static void closeConnection() {
        if (conn != null) {
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * 创意数据库表
     *
     * @param conn           数据库连接
     * @param createTableSQL 建表SQL，可以是多个创建表的SQL组成的SQL串
     *
     * @return
     */
    public static boolean createTable(Connection conn, String createTableSQL) {
        int result = 0;
        try {
            result = qr.update(conn, createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return result > 0;
    }

    /**
     * 动态创建表 注：创建出来的表的格式如：emp_indexname8
     *
     * @param conn       数据库连接
     * @param tableName  表名称，当为｛tableName｝时说明要创建多个表，此参数就会当做一个模板，要与indexName和tableCnt结合一块使用的
     * @param sql        创建表的sql语句
     * @param secondName 表的二级名称，可以按任意类型来分表，例如：emp_shanghai，是按地域分表
     *
     * @return true/false
     */
    public static boolean shardingTable(Connection conn, String tableName, String sql, String secondName) {
        boolean result = false;
        try {
            String newSQL = "";
            String _tableName = "";
            _tableName = tableName + "_" + secondName;
            // 查检库里是否有表名为_tableName的表
            Map<String, Object> isExistResultMap = qr.query(conn, "select count(t.table_name) from information_schema.tables t where t.table_name = ?", new MapHandler(), new Object[]{_tableName});
            if (!isExistResultMap.isEmpty() && isExistResultMap.size() > 0) return false;

            newSQL = sql.replaceFirst("\\{tableName\\}", _tableName);

            // 创建表
            int r = qr.update(conn, newSQL);

            if (r == 0) result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return result;
    }

    /**
     * 从表中获取数据
     *
     * @param conn      数据库连接
     * @param tableName 表名称
     * @param fields    字段名称组成的数组
     *
     * @return K-V 的map对象，K：字段名，V：字段值
     */
    public static Map<String, Object> fetchData(Connection conn, String tableName, String[] fields) {
        StringBuffer columns = new StringBuffer();
        int fieldsLength = fields.length;
        for (int i = 0; i < fieldsLength; i++) {
            columns.append(fields[i]);
            if (i < fieldsLength - 1) {
                columns.append(",");
            }
        }
        String sql = "select " + columns + " from " + tableName;

        Map<String, Object> resultMap = null;
        try {
            resultMap = qr.query(conn, sql, new MapHandler(), new Object[]{});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return resultMap;
    }

    /**
     * 直接用sql查询数据结果
     *
     * @param conn 数据库连接
     * @param sql  完整的查询SQL，这个sql里包括完整的分表名称，也可以加where条件...
     *
     * @return K-V 的map对象，K：字段名，V：字段值
     */
    public static Map<String, Object> fetchData(Connection conn, String sql) {
        Map<String, Object> resultMap = null;
        try {
            resultMap = qr.query(conn, sql, new MapHandler(), new Object[]{});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return resultMap;
    }

}
