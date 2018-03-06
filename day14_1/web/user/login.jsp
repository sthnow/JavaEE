<%--
  Created by IntelliJ IDEA.
  User: Wzz
  Date: 2018/2/15
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>
<body>
<p style="color:red; font-weight:900">${msg}</p>
<form action = "${pageContext.request.contextPath}/LoginServlet" method = "post">
    用户名： <input type="text" name="username" value="${user.username}"/><br/>
    密  码： <input type="password" name="password" value="${user.password}"/><br/>

    <input type="submit" value="登陆"/>
</form>
</body>
</html>
