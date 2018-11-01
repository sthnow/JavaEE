package cn.mldn.lxh.dao.proxy;

import cn.mldn.lxh.dao.IempDAO;
import cn.mldn.lxh.dao.impl.EmpDAOImpl;
import cn.mldn.lxh.dbc.DatabaseConnection;
import cn.mldn.lxh.javabean.Emp;

import java.util.List;

/**
 * 负责数据库的打开和关闭，以及一些业务的处理
 * 之所以增加代理类，是为了以后进行更复杂的业务而准备的
 */
public class EmpDAOProxy implements IempDAO {
    private DatabaseConnection dbc = null;
    private IempDAO dao = null;

    //在代理类的构造方法中实例化数据库连接对象和真实主题实现类
    public EmpDAOProxy() throws Exception {
        this.dbc = new DatabaseConnection();    //实例化数据库连接对象
        this.dao = new EmpDAOImpl(this.dbc.getConnection());    //实例化真实主题类
    }

    @Override
    public boolean doCreate(Emp emp) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.findById(emp.getEmpno()) == null) {
                flag = this.dao.doCreate(emp);
            }else{
                System.out.println("编号重复！");
            }
        } catch (Exception e) {
            throw e;
        }finally{
            this.dbc.close();
        }
        return flag;
    }

    @Override
    public List<Emp> findAll(String keyword) throws Exception {
        List<Emp> all = null;   //定义返回的集合
        try{
            all = this.dao.findAll(keyword);
        }catch (Exception e){
            throw e;
        }finally{
            this.dbc.close();
        }
        return all;
    }

    @Override
    public Emp findById(int empno) throws Exception {
        Emp emp = null;
        try{
            emp = this.dao.findById(empno);
        }catch(Exception e){
            throw e;
        }finally{
            this.dbc.close();
        }
        return emp;
    }
}
