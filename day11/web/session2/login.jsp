<%--
  Created by IntelliJ IDEA.
  User: Wzz
  Date: 2018/2/13
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<h1>登录</h1>

<%
  /*
  读取名为uname的cookie
  如果不为空，显示到输入框中
   */
    String uname = "";
    Cookie[] cs = request.getCookies(); //获取所有cookie
    if(cs != null){  //如果存在cookie
        for(Cookie c : cs){     //循环遍历所有cookie
            if("uname".equals(c.getName())){
                uname = c.getValue();
            }
        }
    }

%>


<%
    String message = "";
    String msg = (String)request.getAttribute("msg");
    if(msg != null){
        message = msg;
    }else{
    }

%>

<font color="red"><b><%=message%></b></font>
<form method="post" action="/day11/loginServlet">
请输入登录名：<input type="text" name="name" value = "<%=uname%>"/><br/>
请输入密码：<input type="password" name="password"/><br/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
