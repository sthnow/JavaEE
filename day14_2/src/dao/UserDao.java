package dao;

import cn.itcast.usermng.domain.User;

/**
 * UserDao接口
 * @author cxf
 *
 */
public interface UserDao {
	public void addUser(User form);
	public User findByUsername(String username);
}
