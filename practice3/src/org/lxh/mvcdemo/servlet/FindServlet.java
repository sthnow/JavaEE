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

@javax.servlet.annotation.WebServlet(name = "FindServlet", urlPatterns = {"/FindServlet"})
public class FindServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "find/result.jsp";     //设置结果转发路径
        String userid = req.getParameter("userid");//获取userid参数
        System.out.println("你查询的userid是:" + userid);
        List<String> info = new ArrayList();

        try {
            if (DAOFactory.getUserDAOInstance().findALL(userid).getUserid() != null) {
               User user = DAOFactory.getUserDAOInstance().findALL(userid);
                info.add("恭喜您,查询成功!");
                req.setAttribute("user", user);
            } else {
                info.add("查询的用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("info", info);
        req.getRequestDispatcher(path).forward(req,resp);
    }

}

