package cn.mldn.lxh.dao;

import cn.mldn.lxh.javabean.Emp;

import java.util.List;

public interface IempDAO {
    /**
     *
     * @param emp 要增加的数据对象
     * @return  是否增加成功的标记
     * @throws Exception
     */
    public boolean doCreate(Emp emp) throws Exception;

    /**
     *
     * @param keyword   查询的关键字
     * @return  返回雇员的JavaBean对象
     * @throws Exception
     */
    public List<Emp> findAll(String keyword) throws Exception;

    /**
     *
     * @param empno 查询的关键字，雇员id
     * @return  返回雇员的JavaBean
     * @throws Exception
     */
    public Emp findById(int empno) throws Exception;
}
