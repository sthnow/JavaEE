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


@javax.servlet.annotation.WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    /* public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         System.out.println("LoginServlet-doGet加载成功");
         String path = "login.jsp";
         String userid = (String) request.getAttribute("userid");
         System.out.println(userid);
         String userpass = (String) request.getAttribute("userpass");
         System.out.println(userpass);
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
         }

         request.setAttribute("info",info);      //保存信息
         request.getRequestDispatcher(path).forward(request,response);
     }
 */
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        System.out.println("LoginServlet-doPost加载成功");
        String path = "/login/result.jsp";
        String userid = request.getParameter("userid");
        System.out.println(userid);
        String userpass = request.getParameter("userpass");
        System.out.println(userpass);
        List<String> info = new ArrayList<String>();

        /*
         * 如果用户id和密码为空的话，返回错误信息
         * 如果不为空，则调用DAO接口判断用户id和密码是否正确
         *      如果正确，返回登陆成功信息
         *      如果不正确，返回登陆失败信息
         */
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
        }

        request.setAttribute("info",info);      //保存信息
        request.getRequestDispatcher(path).forward(request,response);
    }

        //this.doGet(request,response);
    }

