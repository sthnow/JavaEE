package servlet;



import cn.itcast.vcode.utils.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(name = "VerifyCodeServlet", urlPatterns = {"/VerifyCodeServlet"})
public class VerifyCodeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*

         */
        //创建验证码类
        VerifyCode vc = new VerifyCode();
        //得到验证码图片
        BufferedImage image = vc.getImage();
        //把图片上的文本保存到session中
        request.getSession().setAttribute("session_vCode", vc.getText());
        //把图片响应给客户端
        VerifyCode.output(image, response.getOutputStream());
    }
}
