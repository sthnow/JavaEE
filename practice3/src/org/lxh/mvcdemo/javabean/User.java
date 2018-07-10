package org.lxh.mvcdemo.javabean;


public class User {
    /**
     * JavaBean类
     *  提供三个参数，以及setter和getter方法
     *  userid，name，password
     *
     */
    //必须放在包里，没有包的javabean类是不存在的

    private String userid;
    private String name;
    private String password;

    public void setUserid(String userid){
        this.userid = userid;
    }
    public String getUserid(){
        return userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
