package cn.mldn.lxh.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接类,完成数据库的连接
 */
public class DatabaseConnection {
    //准备四大参数
    private static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/mldn?useSSL=false&serverTimezone=UTC";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "123456";

    private Connection conn = null;

    //在构造方法中进行数据库连接
    //好处是不用再调用一个方法进行数据库的连接
    public DatabaseConnection() throws Exception {  //从构造方法中进行数据库连接
        try {
            Class.forName(DBDRIVER);
            this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
        } catch (Exception e) {
            throw e;
        }

    }

    //获取数据库连接
    public Connection getConnection() {
        return this.conn;
    }

    //关闭数据库连接
    public void close() throws SQLException {
        if (this.conn != null) {    //避免不存在数据库时关闭数据库报错
            try {
                conn.close();
            } catch (Exception e) {
                throw e;

            }
        }
    }
}
