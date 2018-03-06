package servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.user.domain.User;
import cn.itcast.user.service.UserException;
import cn.itcast.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistServlet", urlPatterns = {"/RegistServlet"})
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //????????????????????????
        //post请求的编码处理问题
        request.setCharacterEncoding("utf-8");  //设置处理请求的编码
        response.setContentType("text/html;charset=utf-8");     //设置响应的编码

        //servlet依赖service方法
        UserService userService = new UserService();

        //注意这一步的封装表单数据的方法!!!!
        User form = CommonUtils.toBean(request.getParameterMap(), User.class);


        /*
        新添加任务
            校验验证码
               1.用户填写的验证码已经封装到user中
               2.从session获取真正的验证码
               3.比较两者,如果不同,保存错误信息,保存表单数据,转发到regist.jsp
               4.如果相同,什么都不做,向下执行
         */
        String sessionVerifyCode = (String) request.getSession().getAttribute("session_vCode");
        if (!sessionVerifyCode.equalsIgnoreCase(form.getVerifyCode())) {
            request.setAttribute("msg", "验证码错误");
            request.setAttribute("user", form);
            request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
            return;
        }

        try {
            userService.regist(form);
            response.getWriter().print("<h1>注册成功</h1><br/><a href='" +
                    request.getContextPath() +  "/user/login.jsp" + "'> 点击这里完成登陆</a>");
        } catch (UserException e) {
            //获取异常信息,保存到request域中
           request.setAttribute("msg",e.getMessage());
           request.getRequestDispatcher("/user/regist.jsp").forward(request,response);
        }


    }


}
