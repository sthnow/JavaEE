package cn.itcast.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})  //servlet映射路径自带项目名

public class loginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //设置post方法的处理中文问题
        request.setCharacterEncoding("utf-8");
        //获取表单数据
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        //校验是否正确
        if ("itcast".equalsIgnoreCase(name)) {//登录成功
        /*
        如果成功
            保存用户信息到session
            重定向到success1.jsp
            并且把用户名保存到cookie中，发送给浏览器
            当再次打开login.jsp时，显示到用户名文本框中
         */
            Cookie cookie = new Cookie("uname", name);
            cookie.setMaxAge(10);  //设置cookie的生命周期要在添加cookie之前，否则cookie没有生命周期
            response.addCookie(cookie);



            HttpSession session = request.getSession();//获取session
            session.setAttribute("name",name);
            response.sendRedirect("/day11/session1/success1.jsp");
        } else {//登录失败
        /*
        如果失败
            保存错误信息
            转发到login里面
         */
            request.setAttribute("msg", "用户名或密码错误");
            request.getRequestDispatcher("/session2/login.jsp").forward(request,response);
        }
    }
}
