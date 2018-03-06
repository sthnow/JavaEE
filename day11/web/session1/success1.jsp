<%--
  Created by IntelliJ IDEA.
  User: Wzz
  Date: 2018/2/13
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功！</title>
</head>
<body>
<h1>登录成功</h1>

<%
    String name = (String) session.getAttribute("name");
    if( name != null){

    }
    else{
        request.setAttribute("msg", "您还没有登录，不要做坏事");
        request.getRequestDispatcher("/session2/login.jsp").forward(request,response);
        return;
    }
%>

欢迎您，<%=name%>先生
</body>
</html>
