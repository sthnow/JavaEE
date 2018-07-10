package org.lxh.mvcdemo.factory;

import org.lxh.mvcdemo.dao.IUserDAO;
import org.lxh.mvcdemo.dao.proxy.UserDAOProxy;

public class DAOFactory {
    public static IUserDAO getUserDAOInstance(){    //取得DAO实例
        return new UserDAOProxy();  //返回代理实例
    }
}
