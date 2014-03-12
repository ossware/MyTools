package com.ehome.thread;

import com.ehome.model.DataModel;
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
    private long start = 0;              // 起始记录
    private int pageSize = 0;           // 页数据量
    private int processSize = 0;        // 一次批量处理多少数据

    // 处理数据
    public void run() {
        processData(start, pageSize);
    }

    /**
     * 线程分页查询数据
     *
     * @param start
     * @param pageSize
     */
    private void processData(long start, int pageSize) {
        List<DataModel> dataList = new ArrayList<DataModel>();
        PreparedStatement ps = null;
        try {
            conn = DruidUtil.getConnection();
            String sql = "select id, name, type from user limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, start);
            ps.setInt(2, pageSize);
            ResultSet rs = ps.executeQuery();

            DataModel dm;
            while (rs.next()) {
                dm = new DataModel();
                dm.setId(rs.getInt("id"));
                dm.setName(rs.getString("name"));
                dm.setType(rs.getString("type"));
                dataList.add(dm);
//                System.out.println(dm.getId());
            }

            conn.setAutoCommit(false);
            String insertSQL = "insert into u1(name, type) values(?, ?) ";
            ps = conn.prepareStatement(insertSQL);
            for (DataModel data : dataList) {
                ps.setString(1, data.getName());
                ps.setString(2, data.getType());
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(ps);   // 关闭prepareStatement
            DbUtils.closeQuietly(conn); // 关闭数据库连接
        }

    }


    //*******************************************************//
    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
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
