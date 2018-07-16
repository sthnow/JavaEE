package org.lxh.mvcdemo.dao.proxy;

import org.lxh.mvcdemo.dao.IUserDAO;
import org.lxh.mvcdemo.dao.impl.UserDAOImpl;
import org.lxh.mvcdemo.dbc.DatabaseConnection;
import org.lxh.mvcdemo.javabean.User;

import java.sql.Connection;

public class UserDAOProxy implements IUserDAO {
    private DatabaseConnection dbc = null;  //定义数据库连接
    private IUserDAO dao = null;    //定义DAO接口
    boolean flag = false;

    public UserDAOProxy(){
        try{
            this.dbc = new DatabaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.dao = new UserDAOImpl(this.dbc.getConnection());
    }

    @Override
    public boolean findLogin(User user) throws Exception {

        try{
            flag = this.dao.findLogin(user);
        }catch (Exception e){
            throw e;
        }finally{       //在DAO代理操作类中关闭数据库连接
            this.dbc.close();
        }
        return flag;
    }

    @Override
    public boolean findByID(String userid) throws Exception {
        boolean flag = false;
        try{
            flag = this.dao.findByID(userid);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
        return flag;
    }

    @Override
    public boolean doCreate(User user) throws Exception {

        try{
            flag = this.dao.doCreate(user);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
        return  flag;
    }
}
