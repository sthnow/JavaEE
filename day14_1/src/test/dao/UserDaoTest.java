package test.dao;

import cn.itcast.user.dao.UserDao;
import cn.itcast.user.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testByUsername() {
    UserDao userDao = new UserDao();
        User user = userDao.findByUsername("张三");
        System.out.println(user.toString());

    }

    @Test
    public void testAdd() {
        UserDao userDao = new UserDao();

        User user = new User();
        user.setUsername("李四");
        user.setPassword("lisi");
        userDao.add(user);
    }
}
