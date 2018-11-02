package demo1;

import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
public class Demo1 {


    //传统方式
    @Test
    public void run1(){
        UserService userService = new UserServiceImpl();
        userService.sayHello();
    }


    //使用配置文件输出
    @Test
    public void run2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("demo1/applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.sayHello();
    }


    //使用注解的方式输出
    @Test
    public void run3(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("demo1/applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.sayHello();

    }
}
