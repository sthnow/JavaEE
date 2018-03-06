package AbstractTest;

public abstract class AbstractClass {

    //抽象方法中可以包含成员变量
    //默认是包访问权限
     String name;


     //抽象类中可以包含具体的方法
    public void setName(String name){
        this.name = name;
    }

    //但是抽象类中必须有一个抽象的方法
    //该抽象方法由继承该类的类实现
    abstract String getName();
}
