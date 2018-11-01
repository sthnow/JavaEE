package cn.mldn.lxh.javabean;

import java.util.Date;

/**
 * 简单的JavaBean类
 * 包含了对应的setter和getter方法
 */


public class Emp {
    private int empno;
    private String ename;
    private String job;
    private Date date;
    private float sal;

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    //页面传过来的时间是java.util.Date类，
    // 所以这里的set和get方法传入和返回的data类型都要为java.util.Date
    //如果set方法里的是java.util.Date而返回的是java.sql.Date，则会出现类型转换错误的体制
    public Date getDate() {
        return  date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getSal() {
        return sal;
    }

    public void setSal(float sal) {
        this.sal = sal;
    }



    public void setEmpno(int empno){
        this.empno = empno;
    }
    public int getEmpno(){
        return this.empno;
    }
}
