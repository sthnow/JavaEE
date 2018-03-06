<%--
  Created by IntelliJ IDEA.
  User: Wzz
  Date: 2018/2/15
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>welcome</title>
</head>
<body>
<h1>欢迎登陆本系统</h1>
<c:choose>
    <c:when test="${empty sessionScope.sessionUser}">滚</c:when>
    <c:otherwise>${sessionScope.sessionUser}</c:otherwise>
</c:choose>

</body>
</html>
