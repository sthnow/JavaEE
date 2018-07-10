package org.lxh.mvcdemo.dao.impl;

import org.lxh.mvcdemo.dao.IUserDAO;
import org.lxh.mvcdemo.javabean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAOImpl implements IUserDAO {
    //数据库真是实现类
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    //从构造器中获取连接
    public UserDAOImpl(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param user 表单提交的javabean
     * @return 验证操作结果
     * @throws Exception 抛出异常
     */
    @Override
    public boolean findLogin(User user) throws Exception {
        boolean flag = false;
        try {
            String sql = "SELECT name FROM user WHRER userid=? AND password=?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, user.getUserid());
            this.pstmt.setString(2, user.getPassword());
            ResultSet rs = this.pstmt.executeQuery();
            if (rs.next()) {      //如果能查询到信息
                user.setName(rs.getString(1));
                System.out.println("设置user的name为:" + rs.getString(1));
                flag = true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (pstmt != null) {
                this.pstmt.close();
            }
        }
        return flag;
    }
}
