package test;

import org.junit.Test;

import java.sql.*;

import static java.lang.Class.forName;

public class TestQuery {

    @Test
    public void query() throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        //获取connection对象
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu", "root", "611101");

            //通过connection对象获取statement对象，然后就可以执行语句
            stmt = con.createStatement();

            //创建sql语句
            String sql = "select * from stu";
            //执行sql语句
            rs = stmt.executeQuery(sql);


            rs.last();      //将光标位置放到最后一行，以便获取数据库总共的行数
            int lineNum = rs.getRow();
            rs.beforeFirst();   //将光标放到第一行之前

            //获取集元数据,以便获取总共的列数
            int columnNum = rs.getMetaData().getColumnCount();

            //数据库的第一行从1开始
            //打印结果集
            while (rs.next()) {     //遍历行
                for (int i = 1; i <= columnNum; i++) {        //遍历列
                    System.out.print(rs.getString(i));
                    if (i < columnNum) {
                        System.out.print(",");
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) rs.close();
            if(stmt != null)stmt.close();
            if(con != null)con.close();
        }
    }
}