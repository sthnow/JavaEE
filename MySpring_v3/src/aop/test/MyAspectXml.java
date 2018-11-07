package aop.test;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;

//切面类
//切面类的注解
//在注解中表明切面类
@Aspect
public class MyAspectXml {


    //通过注解选择通知的织入方式和设置切入点
    @Before(value = "execution(public void aop.test.OrderDaoImpl.save())")
    public void before(){
        System.out.println("前置增强");
    }

}
