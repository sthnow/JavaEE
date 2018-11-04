package aop.test;


//直接编写实现类

import org.springframework.stereotype.Component;

@Component(value = "userService")
public class UserServiceImpl {

    //切入点
    public void save() {
        System.out.println("保存客户");
    }


    //连接点
    public void update(){
        System.out.println("更新客户");
    }

}
