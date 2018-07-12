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

@javax.servlet.annotation.WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})

public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 功能:
         *  获取register.jsp页面穿过来的参数
         *  调用DAO提供的add方法,将信息添加到数据库中
         *  返回添加成功或失败的信息,并提供跳转到登陆的接口
         */
        System.out.println("加载RegisterServlet");

        String userid = req.getParameter("userid");
        String username = req.getParameter("username");
        String userpass = req.getParameter("userpass");
        String path = "/register/result.jsp";
        List<String> info = new ArrayList<>();  //保存操作信息

        if(userid == null || "".equals(userid)){
            info.add("用户id不能为空");
        }
        if(username == null || "".equals(username)){
            info.add("用户名不能为空");
        }
        if (userpass == null || "".equals(userpass)) {
            info.add("用户密码不能为空");
        }

        if(info.size() == 0){   //不能info==null作为条件,应该写info.size()==0

            System.out.println("注册信息正确,开始注册");

            User user = new User();
            user.setUserid(userid);
            user.setName(username);
            user.setPassword(userpass);

            try{
                if(DAOFactory.getUserDAOInstance().addUser(user)){
                    info.add("用户信息添加成功");
                }
                else{
                    info.add("用户信息添加失败");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        req.setAttribute("info", info);
        req.getRequestDispatcher(path).forward(req,resp);

    }
}
