<%--
  Created by IntelliJ IDEA.
  User: Wzz
  Date: 2018/7/16
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询用户</title>
</head>
<body>
<form action="../FindServlet" method="post">
    请输入查询的用户ID:<input type="text" name="userid"><br>
    <input type="submit" value="提交">
    <input type="reset" value="重置">
</form>
</body>
</html>
