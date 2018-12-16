package cn.itheima.controller;


import cn.itheima.pojo.Items;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//使用注解表明这是一个控制器
@Controller

//窄化请求映射路径
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/login")
    public String login() throws Exception{

        //如果返回的是字符串，SpringMVC会默认匹配和字符串相同的页面名称
        return "login";
    }


    @RequestMapping(value="/submit")
    public String submit(String username, String pwd, HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();

//        校验用户名和密码的正确性，若正确则将登陆信息放入Session中
//        这里简化，只判断用户名和密码是否为空，真正写的时候需要调用数据库验证登录名和密码的正确性
        if(username != null){
            session.setAttribute("username", username);
        }

//        使用字符串的方式实现重定向或者请求转发，这里也可以使用HttpServletResponse实现重定向
//        如果使用HttpServletResponse实现重定向，则不经过SpringMVC，因此要写页面的全路径名称
//        response.sendRedirect();
        return "redirecct:/items/list";
    }


}
