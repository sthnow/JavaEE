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
            String sql = "SELECT name FROM user WHERE userid=? AND password=?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, user.getUserid());
            this.pstmt.setString(2, user.getPassword());
            ResultSet rs = this.pstmt.executeQuery();
            if (rs.next()) {      //如果能查询到信息
                user.setName(rs.getString(1));
                System.out.println("用户名为：" + rs.getString(1));
                flag = true;
            }
        } catch (Exception e) {
            throw e;
        } finally {     //在DAO真实实现类中关闭 pstmt声明语句
            if (pstmt != null) {
                this.pstmt.close();
            }
        }
        return flag;
    }

    @Override
    public boolean findByID(String userid) throws Exception {
        boolean flag = false;
        try {
            String sql = "SELECT * FROM user where userid = ?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, userid);
            ResultSet rs = this.pstmt.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        }catch (Exception e){
            System.out.println("查询id语句出现异常");
            throw e;
        }finally {
            if(this.pstmt != null)
            this.pstmt.close();
        }
        return flag;
    }


    @Override
    public boolean doCreate(User user) throws Exception {
        /**
         * 添加用户的方法
         * 如果添加成功, 返回true   若失败,返回true
         */
        boolean flag = false;
        try {
            //准备sql模板
            String sql = "INSERT INTO user(userid,name,password) VALUES (?,?,?)";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1, user.getUserid());
            this.pstmt.setString(2, user.getName());
            this.pstmt.setString(3, user.getPassword());
            if (this.pstmt.executeUpdate() > 0) {   //如果更新记录的行数大于0
                flag = true;    //修改标志位为true，表示更新成功
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (this.pstmt != null) {
                this.pstmt.close();
            }
        }
        return flag;
    }

    @Override
    public User findALL(String keyword) throws Exception {
        User user = new User();
        try {
            String sql = "SELECT * from user where userid = ?";
            this.pstmt = this.conn.prepareStatement(sql);
            this.pstmt.setString(1,  keyword );
            ResultSet rs = this.pstmt.executeQuery();
            if (rs.next()) {
                user.setUserid(rs.getString(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));
            }
        } catch (Exception e) {
            throw e;
        }
        return user;
    }


}
