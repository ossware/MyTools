package com.ihome.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: haoxiaolei
 * Date: 13-1-14
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 * JDBC SQL 设置参数
 */
public class ParamterUtils {

    /**
     * 自动生成参数占位符(?)
     * 适用于sql中出现 in 而在 PreparedStatement中不能set一组值的问题
     * @param size
     * @return
     */
    public static String createParamMark(Integer size) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append("?");
            if (i < size - 1) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    /**
     * 设置参数列表
     * @param paramters 可以放不同类型的参数
     * @param ps
     * @throws java.sql.SQLException
     */
    public static void setParamters(List<?> paramters, PreparedStatement ps) throws SQLException {
        if (null != paramters && paramters.size() > 0) {
            int paramSize = paramters.size();
            Object param = null;
            for (int i = 0; i < paramSize; i++) {
                param = paramters.get(i);
                if (param instanceof Integer) {
                    ps.setInt(i + 1, Integer.valueOf(param.toString()));
                } else if (param instanceof Long) {
                    ps.setLong(i + 1, Long.valueOf(param.toString()));
                } else if (param instanceof String) {
                    ps.setString(i + 1, param.toString());
                } else if (param instanceof Double) {
                    ps.setDouble(i + 1, Double.valueOf(param.toString()));
                } else if (param instanceof Float) {
                    ps.setFloat(i + 1, Float.valueOf(param.toString()));
                } else if(param instanceof Date) {
                    try {
                        ps.setDate(i + 1, (java.sql.Date) new SimpleDateFormat().parse(param.toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("日期转换错误！请查检日期字符串格式！");
                    }
                }
            }
        }
    }

    /**
     * createParamMark() 和 setParamters()方法的使用
     */
    public boolean useage(List<?> idList) throws SQLException {
        boolean resultFlag = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conn = DruidUtil.getConnection();
            String paramsMark = createParamMark(idList.size()); // 初始化参数占位符

            String sql = "select * from user t where t.id in (" + paramsMark + ")";
            ps = conn.prepareStatement(sql);
            setParamters(idList, ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("userName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultFlag;
    }







}
