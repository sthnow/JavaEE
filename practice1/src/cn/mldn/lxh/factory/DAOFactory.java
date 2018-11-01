package cn.mldn.lxh.factory;

import cn.mldn.lxh.dao.IempDAO;
import cn.mldn.lxh.dao.proxy.EmpDAOProxy;

/**
 * 所有DAO接口的实例都通过工厂类获得
 */
public class DAOFactory {
    public static IempDAO getIEmpDAOInstance() throws Exception{ //取得DAO接口实例
        return new EmpDAOProxy();   //取得代理类的实例
    }
}
