package demo1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component(value = "userService")
public class UserServiceImpl implements UserService {

    @Override
    public void sayHello() {
        System.out.println("UserService实现类输出");
    }
}
