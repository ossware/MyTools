/**----------------------------------------------------------------
 // Copyright (C) 2014 北京品众互动网络营销技术有限公司版权所有。
 // @project: MyTools
 // @package: PACKAGE_NAME
 // @className: DruidUtilTest
 //
 // @author: haoxiaolei
 // @date： 14-2-21 下午5:01
 // @version: v1.0
 //----------------------------------------------------------------*/

import com.ihome.util.DruidUtil;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package PACKAGE_NAME
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 14-2-21 下午5:01
 * @Copyright: 2014 ihome.com
 */
public class DruidUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getConnection() {
        Connection conn;
        try {
            conn = DruidUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveTest() {
        String sql = "insert into user(name, type) values(?,?)";
        try {
            Connection conn = DruidUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < 20000; i++) {
                if (i <= 5000) {
                    ps.setString(1, "单车上的理想");
                    ps.setString(2, "default");
                } else {
                    ps.setString(1, "单车上的理想");
                    ps.setString(2, "360");
                }
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();

            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
