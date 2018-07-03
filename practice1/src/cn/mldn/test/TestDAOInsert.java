package cn.mldn.test;

import cn.mldn.lxh.factory.DAOFactory;
import cn.mldn.lxh.javabean.Emp;

public class TestDAOInsert {
    public static void main(String[] args) throws Exception {
        Emp emp = null;
        for (int x = 10; x < 15; x++) {
            emp = new Emp();
            emp.setEmpno(1 + x);
            emp.setEname("李兴-" + x);
            emp.setJob("程序员-" +x);
            emp.setDate(new java.sql.Date(2001));
            emp.setSal(500 * x);
            DAOFactory.getIEmpDAOInstance().doCreate(emp);
        }
    }
}
