package cn.itcast.user.service;

/*
对异常来说,既不关心他的构造器,也不关心他的方法,只关心他的名字
因为通过名字可以判断出哪里出错误

所以自定义一个异常类,把父类的构造器拿过来,改下名字就可以
 */

/*
同一个类的两个异常对象有什么区别__区别在于message不同
 */
public class UserException extends Exception{
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

}
