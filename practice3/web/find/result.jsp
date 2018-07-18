<%@page import="org.lxh.mvcdemo.javabean.User" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Wzz
  Date: 2018/7/16
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询结果</title>
</head>
<body>
<%
    List<String> info = (List<String>) request.getAttribute("info");
    if (info != null) {
        Iterator<String> iter = info.iterator();
        while (iter.hasNext()) {
%>

<h4><%=iter.next()%></h4>

<%
        }
    }
    User user = (User) request.getAttribute("user");
    if (request.getAttribute("user") != null){
        %>
<table>
    <tr>
        <td>用户ID:</td>
        <td><%=user.getUserid()%></td>
    </tr>
    <tr>
        <td>用户名称:</td>
        <td><%=user.getName()%></td>
    </tr>
    <tr>
        <td>用户密码:</td>
        <td><%=user.getPassword()%></td>
    </tr>
</table>
<%
    }
%>
</body>
</html>
