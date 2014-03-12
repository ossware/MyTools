/**----------------------------------------------------------------
 // Copyright (C) 2013 北京品众互动网络营销技术有限公司版权所有。
 // @project: MyTools
 // @package: PACKAGE_NAME
 // @className: ShardingUtilTest
 //
 // @author: haoxiaolei
 // @date： 13-10-26 下午6:32
 // @version: v1.0
 //----------------------------------------------------------------*/

import com.ihome.sharding.config.DBUtil;
import com.ihome.sharding.config.Database;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package PACKAGE_NAME
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 13-10-26 下午6:32
 * @Copyright: 2013 ihome.com
 */
public class ShardingUtilTest {

    Connection conn = null;
    QueryRunner query = new QueryRunner();

    @Before
    public void init() {
        Database database = new Database(Database.Dialect.MYSQL, "127.0.0.1", 3306, "sharding", "root", "root", "utf8");
        conn = DBUtil.getConnection(database);
    }

    @Test
    public void createTable() {
        String sql = "";
        for (int i = 1; i <= 1; i++) {
            sql = "CREATE TABLE t_" + i + "(" +
                    "  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识'," +
                    "  name varchar(255) DEFAULT NULL COMMENT '名称'," +
                    "  age int(3) DEFAULT NULL COMMENT '年龄'," +
                    "  birthday date DEFAULT NULL COMMENT '出生日期'," +
                    "  PRIMARY KEY (id)" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n";
            DBUtil.createTable(conn, sql);
        }
    }

    @Test
    public void createTableBatch() {
        String sql = "CREATE TABLE {tableName}(" +
                        "  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识'," +
                        "  name varchar(255) DEFAULT NULL COMMENT '名称'," +
                        "  age int(3) DEFAULT NULL COMMENT '年龄'," +
                        "  birthday date DEFAULT NULL COMMENT '出生日期'," +
                        "  PRIMARY KEY (id)" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n";
        boolean result = DBUtil.shardingTable(conn, "emp", sql, "shanghai");
        System.out.println(result);
    }

    @Test
    public void fetchData() {
        DBUtil.fetchData(conn, "emp_shanghai_1", new String[]{"id", "name", "age"});
    }
}
