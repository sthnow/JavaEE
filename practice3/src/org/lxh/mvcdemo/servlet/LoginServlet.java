package org.lxh.mvcdemo.servlet;

import org.lxh.mvcdemo.factory.DAOFactory;
import org.lxh.mvcdemo.javabean.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "login.jsp";
        String userid = (String) request.getAttribute("userid");
        String userpass = (String) request.getAttribute("userpass");
        List<String> info = new ArrayList<String>();
        if (userid == null || "".equals(userid)) { //如果用户名为null
            info.add("用户名id不能为空!");
        }
        if (userpass == null || "".equals(userpass)) {    //如果密码为null
            info.add("密码不能为空!");
        }
        if (info.size() == 0) {
            User user = new User(); //实例化javabean
            user.setUserid(userid);
            user.setPassword(userpass);
            try {
                if (DAOFactory.getUserDAOInstance().findLogin(user)) {     //如果验证通过
                    info.add("用户登录成功,欢迎:" + user.getName());
                }
                else{
                    info.add("用户登陆失败,错误的用户名和密码");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setAttribute("info",info);      //保存错误信息
            request.getRequestDispatcher(path).forward(request,response);
        }
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
