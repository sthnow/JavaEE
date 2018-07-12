<%--
  Created by IntelliJ IDEA.
  User: Wzz
  Date: 2018/7/11
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.Iterator   " %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>登陆页面</title>
</head>
<script language="JavaScript">
    function validate(f) {
        if (!(/^\w{5,15}$/.test(f.userid.value()))) {
            alert("用户id必须是5-15位");
            f.userid.focus;
            return false;
        }
        if (!(/^\w{5,15}/.test(f.userpass.value()))) {
            alert("密码必须为5-15位");
            f.userpass.focus;
            return false;
        }
        return true;
    }
</script>
<body>
<h2>用户登陆</h2>
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
<form action="../LoginServlet" method="post">

    用户ID:<input type="text" name="userid" ><br>
    密&nbsp;&nbsp;&nbsp;码:<input type="password" name="userpass"><br>

    <input type="submit" value="登陆">
    <input type="reset" value="重置">
    <a href="../register/register.jsp">注册</a>
</form>
</body>
</html>
