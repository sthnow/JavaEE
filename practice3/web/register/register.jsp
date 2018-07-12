<%--
  Created by IntelliJ IDEA.
  User: Wzz
  Date: 2018/7/11
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
</head>
<body>
<h2>用户注册</h2>
<form action="../RegisterServlet" method="post">

    用户id:<input type="text" name="userid"><br>
    用户名:<input type="text" name="username"><br>
    密&nbsp;&nbsp;&nbsp;码:<input type="password" name="userpass"><br>

    <input type="submit" value="提交">
    <input type="reset" value="重置">
    <a href="../login/login.jsp">登陆</a>

</form>
</body>
</html>
