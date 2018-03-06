package test;

import org.junit.Test;

import cn.itcast.usermng.dao.UserDao;
import cn.itcast.usermng.domain.User;

/**
 * UserDao的测试类
 * @author cxf
 *
 */
public class UserDaoTest {
	@Test
	public void testAddUser() {
		UserDao userDao = new UserDao();
		
		User form = new User();
		form.setUsername("李四");
		form.setPassword("456");
		form.setAge(92);
		form.setGender("女");
		
		userDao.addUser(form);
	}
	
	@Test
	public void testFindByUsername() {
		UserDao userDao = new UserDao();
		
		User user = userDao.findByUsername("liSi");
		
		System.out.println(user);
	}
}
