package cn.mldn.lxh.factory;

import cn.mldn.lxh.dao.IempDAO;
import cn.mldn.lxh.dao.proxy.EmpDAOProxy;

public class DAOFactory {
    public static IempDAO getIEmpDAOInstance() throws Exception{ //取得DAO接口实例
        return new EmpDAOProxy();   //取得代理类的实例
    }
}
