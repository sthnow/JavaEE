package cn.itcast.demo;

import cn.itcast.jdbc.JdbcUtils;

import org.junit.Test;


import java.sql.Connection;
import java.sql.SQLException;

public class Demo1Service {

    public void zhuanZhang(String from, String to, double money) throws SQLException {
        Connection con = null;
        try{
            con = JdbcUtils.getConnection();
            //开启事务
            //。。。
            //提交事务
            con.setAutoCommit(false);
            AccountDao ad = new AccountDao();
            ad.updateBalance(con, from, -money);
            ad.updateBalance(con, to, money);
            con.commit();

        }catch(Exception e){
            //回滚事务
            con.rollback();
        }

    }
@Test
    public void fun1() throws SQLException {
       zhuanZhang("zs","ls",100);
    }
}




