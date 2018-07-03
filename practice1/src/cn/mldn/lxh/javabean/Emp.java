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

    public java.sql.Date getDate() {
        return (java.sql.Date) date;
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
