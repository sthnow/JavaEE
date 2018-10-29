/**
 * 实现了UserService接口
 */
public class UserServiceIml implements UserService {

    //使用传统的方式创建对象
    @Override
    public void say() {
        System.out.println("传统方法创建对象");
    }
}
