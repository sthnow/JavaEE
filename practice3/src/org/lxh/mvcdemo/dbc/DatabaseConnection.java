package org.lxh.mvcdemo.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    /**
     * 数据库操作类，负责数据库的连接与关闭
     */

    //准备四大参数
    private static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/mldn?useSSL=false&serverTimezone=UTC";
    private static final String DBUSER = "root";;
    private static final String DBPASSWORD = "123456";

    private Connection conn = null;

    //从构造器中初始化数据库连接
    public DatabaseConnection() throws Exception{
        try{
            Class.forName(DBDRIVER);
            this.conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
        }catch (Exception e){
            throw e;
        }
    }

    //获取连接的方法
    public Connection getConnection(){
        return this.conn;
    }

    //关闭数据库连接的方法
    public void close() throws SQLException {
        if(conn != null){
            try{
                this.conn.close();
            }catch(Exception e){
                throw e;
            }
        }
    }
}


