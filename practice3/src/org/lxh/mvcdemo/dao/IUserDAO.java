package org.lxh.mvcdemo.dao;

import org.lxh.mvcdemo.javabean.User;

public interface IUserDAO {
    /**
     * 定义DAO接口
     */
    public boolean findLogin(User user) throws Exception;

    public boolean addUser(User user) throws Exception;
}
