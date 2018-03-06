package cn.itcast.user.service;

import cn.itcast.user.dao.UserDao;
import cn.itcast.user.domain.User;


/*
    业务逻辑层
        功能:实现一些业务逻辑
 */

public class UserService {

    private UserDao userDao = new UserDao();


    //注册功能
    public void regist(User user) throws UserException{  //在方法声明中抛出异常

        User _user = userDao.findByUsername(user.getUsername());
        //主动抛出异常
        if( _user != null) throw new RuntimeException("用户名" + "  " + user.getUsername() + "  " + ",已经被注册过了");

        userDao.add(user);
    }


    //登陆功能
    public User login(User form) throws UserException {
       User user = userDao.findByUsername(form.getUsername());
        if(user == null) throw new UserException("用户名不存在");
        if (!form.getPassword().equals(user.getPassword())) {
            throw new UserException("密码错误");
        }
    return user;
    }

}
