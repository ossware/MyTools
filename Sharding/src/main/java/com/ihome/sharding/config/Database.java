/**----------------------------------------------------------------
 // Copyright (C) 2013 北京品众互动网络营销技术有限公司版权所有。
 // @project: MyTools
 // @package: com.ihome.sharding.config
 // @className: Database
 //
 // @author: haoxiaolei
 // @date： 13-10-26 下午5:15
 // @version: v1.0
 //----------------------------------------------------------------*/
package com.ihome.sharding.config;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title: 数据库对象信息
 * @Package com.ihome.sharding.config
 * @Description: 填充数据库的相关属性
 * @Author xiaolei-0228@163.com
 * @Date 13-10-26 下午5:15
 * @Copyright: 2013 ihome.com
 */
public class Database {

    /**
     * 方言
     */
    private String dialect;

    /**
     * 驱动
     */
    private String driver;

    /**
     * 数据库所在的主机
     */
    private String server;

    /**
     * 端口
     */
    private int port;

    /**
     * 数据库名称
     */
    private String db;

    /**
     * 数据库用户名
     */
    private String uid;

    /**
     * 数据库密码
     */
    private String pwd;

    /**
     * 数据库编码
     */
    private String encoding = "utf8";


    public Database(String dialect, String server, int port, String db, String uid, String pwd, String encoding) {
        this.dialect = dialect;
        this.server = server;
        this.port = port;
        this.db = db;
        this.uid = uid;
        this.pwd = pwd;
        if (encoding != null) this.encoding = encoding;
    }

    public Database(String dialect, String server, int port, String db, String uid, String pwd) {
        this.dialect = dialect;
        this.server = server;
        this.port = port;
        this.db = db;
        this.uid = uid;
        this.pwd = pwd;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getDriver() {
        if (dialect.equals(Dialect.MYSQL))
            driver = "com.mysql.jdbc.Driver";
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * 获取jdbcUrl数据库连接字符串
     *
     * @return
     */
    public String getJDBCUrl() {
        return dialect + server + ":" + port + "/" + db;
    }


    /**
     * 数据库方言常量类
     */
    public class Dialect {
        public static final String MYSQL = "jdbc:mysql://";
    }
}
