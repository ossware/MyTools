package com.ehome.thread;

import com.ehome.util.DruidUtil;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package com.ehome.thread
 * @Description: 处理数据的线程
 * @Author xiaolei-0228@163.com
 * @Date 14-2-26 下午3:35
 * @Copyright: 2014 ihome.com
 */
public class ProcessDataThread implements Runnable {
    Connection conn = null;
    private String operateType;         // 操作类型
    private int start = 0;              // 起始记录
    private int pageSize = 0;           // 页大小
    private int processSize = 0;        // 一次批量处理多少数据

    // 处理数据
    public void run() {
        if (operateType.equals("insert")) {
            insertData();
        } else {
            queryData(start, pageSize);
        }
    }

    /**
     * 批量把一页数据插入数据库
     */
    public void insertData() {
        PreparedStatement ps = null;
        try {
            conn = DruidUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into user(name, type) values(?, ?)";
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= processSize; i++) {
                ps.setString(1, "单车上的理想_" + Thread.currentThread().getName());
                ps.setString(2, "线程_" + Thread.currentThread().getName());
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DbUtils.rollback(conn);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            DbUtils.closeQuietly(ps);   // 关闭prepareStatement
            DbUtils.closeQuietly(conn); // 关闭数据库连接
        }
    }

    /**
     * 线程分页查询数据
     *
     * @param start
     * @param pageSize
     */
    public void queryData(int start, int pageSize) {
        List<Object> dataList = new ArrayList<Object>();
        PreparedStatement ps = null;
        try {
            conn = DruidUtil.getConnection();
            String sql = "select id from user limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dataList.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(ps);   // 关闭prepareStatement
            DbUtils.closeQuietly(conn); // 关闭数据库连接
        }

        //////////////////////////////
        for (Object obj : dataList) {
            System.out.println(Thread.currentThread().getName() + ", id：" + obj);
        }
    }


    //*******************************************************//
    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getProcessSize() {
        return processSize;
    }

    public void setProcessSize(int processSize) {
        this.processSize = processSize;
    }
}
