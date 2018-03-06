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
    <title>注册</title>

    <script type="text/javascript">
        function  _change() {
            //1.获取image元素
            var ele = document.getElementById("vCode");
            ele.src = "/day14_1/VerifyCodeServlet?xxx=" + new Date().getTime();

        }
    </script>


</head>
<body>
<h1>请您注册</h1>
<p style="color:red; font-weight:900">${msg}</p>
<form action = "${pageContext.request.contextPath}/RegistServlet" method = "post">
    用户名： <input type="text" name="username" value="${user.username}"/>${errors.username}<br/>
    密  码： <input type="password" name="password" value="${user.password}"/>${errors.password}}<br/>


    验证码:<input type="text" name="verifyCode" value="${user.verifyCode}" size="3"/>

    <img id="vCode" src="/day14_1/VerifyCodeServlet" border="2"/>
    <a href="javascript:_change()">换一张</a><br/>
    <input type="submit" value="点击注册"/>
</form>


</body>
</html>
