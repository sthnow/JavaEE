<!--<%@page import="cn.mldn.test.UserBean" %>-->
<!--标签指令,必须结束-->
<jsp:useBean id="ub" scope="page" class="cn.mldn.test.UserBean"/>
<jsp:setProperty name="ub" property="*"/>
<%--
  Created by IntelliJ IDEA.
  User: Wzz
  Date: 2018/6/30
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试JavaBean</title>
</head>
<body>

姓名:<%=ub.getName()%>
密码:<%=ub.getPassword()%>
</body>
</html>
