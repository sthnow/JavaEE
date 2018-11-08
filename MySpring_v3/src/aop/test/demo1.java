package aop.test;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class demo1 {
    @Test
    public void run1(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop/test/applicationContext.xml");
        UserServiceImpl userService = (UserServiceImpl) applicationContext.getBean("userService");
        userService.save();

    }

    @Test
    public void run2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop/test/applicationContext2.xml");
                 OrderDao orderDao = (OrderDao) applicationContext.getBean("orderDao");
                orderDao.save();
    }

}
