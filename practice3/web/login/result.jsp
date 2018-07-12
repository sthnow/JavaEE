<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Wzz
  Date: 2018/7/11
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆成功</title>
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
%>
<center>


</center>


</body>
</html>
