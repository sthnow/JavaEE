package cn.itcast.demo;

import cn.itcast.jdbc.JdbcUtils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AccountDao {


    //修改指定用户的余额
    public void updateBalance(Connection con , String name, Double balance) {
        try {

            /*
                1.得到连接对象
                2.创建sql模板
                3.创建psmt对象
                4.执行
             */
            String sql = "update account set balance =  balance + ? where name = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1, balance);
            pstmt.setString(2, name);

            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
