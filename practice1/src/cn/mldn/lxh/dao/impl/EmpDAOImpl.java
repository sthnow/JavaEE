package cn.mldn.lxh.dao.impl;

import cn.mldn.lxh.dao.IempDAO;
import cn.mldn.lxh.javabean.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  真实主题实现类，实现数据库的具体操作
 *  数据库的打开和关闭则由代理类完成
*/
public class EmpDAOImpl implements IempDAO {

    private Connection conn = null; //初始化数据库连接对象
    private PreparedStatement pstmt = null; //初始化数据库操作对象

    public EmpDAOImpl(Connection conn) { //通过构造方法取得数据库连接
        this.conn = conn;
    }

    @Override
    public boolean doCreate(Emp emp) throws SQLException {
        boolean flag = false;
        String sql = "INSERT INTO emp(empno,ename,job,hiredate,sal) VALUES(?,?,?,?,?)";
        //实例化preparedstament对象
        this.pstmt = this.conn.prepareStatement(sql);   //执行语句的时候可能会发生异常，所以需要抛出
        this.pstmt.setInt(1, emp.getEmpno());
        this.pstmt.setString(2, emp.getEname());
        this.pstmt.setString(3, emp.getJob());
        this.pstmt.setDate(4, new java.sql.Date(emp.getDate().getTime()));
        this.pstmt.setFloat(5, emp.getSal());
        if(this.pstmt.executeUpdate()>0){   //如果更新记录的行数大于0
            flag = true;    //修改标志位为true，表示更新成功
        }
        this.pstmt.close(); //关闭pstmt连接
        return flag;
    }

    @Override
    public List<Emp> findAll(String keyword) throws Exception {
        List<Emp> all = new ArrayList<Emp>();
        String sql = "SELECT empno,ename,job,hiredate,sal FROM emp WHERE ename LIKE ? OR job LIKE ?";
        this.pstmt = conn.prepareStatement(sql);    //实例化pstmt对象
        this.pstmt.setString(1, "%" + keyword + "%");   //设置查询关键字
        this.pstmt.setString(2, "%" + keyword + "%");
        ResultSet rs = this.pstmt.executeQuery();   //执行查询操作
        Emp emp = null; //初始化emp对象
        while(rs.next()){   //如果查询到数据,依次取出
            emp = new Emp();    //定义一个新的emp对象
            emp.setEmpno(rs.getInt(1));
            emp.setEname(rs.getString(2));
            emp.setJob(rs.getString(3));
            emp.setDate(rs.getDate(4));
            emp.setSal(rs.getFloat(5));
            all.add(emp);   //向集合中添加对象
        }
        this.pstmt.close();
        return all;
    }

    /**
     * 按编号查询,如果查询到对象,则实例化Emp对象,并将内容取出赋予Emp对象中的属性;如果没有则返回null
     * @param empno 查询的关键字，雇员id
     * @return
     * @throws Exception
     */
    @Override
    public Emp findById(int empno) throws Exception {
        Emp emp = null;
        String sql = "SELECT empno,ename,job,hiredate,sal FROM emp WHERE empno=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, empno);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){   //如果查询到对象
            emp = new Emp();    //实例化新的emp对象
            emp.setEmpno(rs.getInt(1));
            emp.setEname(rs.getString(2));
            emp.setJob(rs.getString(3));
            emp.setDate(rs.getDate(4));
            emp.setSal(rs.getFloat(5));
               //如果查询不到结果则返回null,默认返回为null
        }
        return emp;
    }
}
