import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {

    @Test
    public void run1() {

        //本类的引用指向本类的实现
//        UserServiceIml userServiceIml = new UserServiceIml();
//        userServiceIml.say();


        //使用多态的方式，将接口的引用指向实现接口的子类
        UserService userService = new UserServiceIml();
        userService.say();

    }

    /**
     * 使用Spring IOC 模式
     */
    @Test
    public void run2(){
        //创建工厂，加载核心配置文件
        //Classpath 默认是src路径下
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从工厂中获取对象
        //读取的时候就已经配置好了
        UserService userService = (UserService) ac.getBean("UserService");

        //调用对象的方法执行
        userService.say();
    }
}
