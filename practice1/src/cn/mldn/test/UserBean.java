package cn.mldn.test;

public class UserBean {

    public UserBean(){
        //System.out.println("这是一个无参的构造方法");
    }
    //两个封装的属性
    private String name;
    private String password;

    //对应的setter和getter方法
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    //会自动生成一个无参的构造方法
}
