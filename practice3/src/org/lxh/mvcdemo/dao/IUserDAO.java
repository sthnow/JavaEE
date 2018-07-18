package org.lxh.mvcdemo.dao;

import org.lxh.mvcdemo.javabean.User;

public interface IUserDAO {
    /**
     *
     * @param user  传入的JavaBean对象
     * @return  返回标志位
     * @throws Exception    显示抛出异常
     */
    public boolean findLogin(User user) throws Exception;


    public boolean findByID(String userid) throws Exception;

    /**
     *
     * @param user  传入JavaBean对象
     * @return      返回标志位
     * @throws Exception    显示抛出异常
     */
    public boolean doCreate(User user) throws Exception;

    public User findALL(String keyword) throws Exception;
    //public boolean doDelete();
}
