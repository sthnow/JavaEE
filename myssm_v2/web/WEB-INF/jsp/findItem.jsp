<%--
  Created by IntelliJ IDEA.
  User: wangz
  Date: 2018/12/16
  Time: 0:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询页面</title>
</head>
<body>
<form id="itemForm"	action="${pageContext.request.contextPath }/items/find" method="get">
    <div align="middle" align="center">
    <input type="text" name="id" /> 请输入要查询商品的id：
    </div>
</form>
</body>
</html>
