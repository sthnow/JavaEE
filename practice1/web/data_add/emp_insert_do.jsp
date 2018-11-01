<%@ page import="cn.mldn.lxh.factory.DAOFactory" %>
<%@ page import="cn.mldn.lxh.javabean.Emp" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Wzz
  Date: 2018/6/30
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>完成增加雇员的操作</title>
</head>
<%
    request.setCharacterEncoding("UTF-8");    //对客户端的请求进行解码
%>
<body>
<%
    Emp emp = new Emp();    //实例化Emp对象
    emp.setEmpno(Integer.parseInt(request.getParameter("empno")));
    emp.setEname(request.getParameter("ename"));
    emp.setJob(request.getParameter("job"));
    emp.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("hiredate")));
    emp.setSal(Float.parseFloat(request.getParameter("sal")));
    try {
        //在doCreate方法中添加标志位的好处,可以在以后调用该方法的时候通过if语句判断是否执行成功
        //这样的好处就是可以更容易知道错误出在哪里
        //如果不添加标志位只知道一条错误信息,这样可以多一条明显的"添加失败"的信息
        //!! 注意这里的错误信息给出是能在客户端上显示的，如果这里没有标志位不能判断是否执行成功，给出的错误信息只能在后台显示
        if (DAOFactory.getIEmpDAOInstance().doCreate(emp)) {
%>
<h3>雇员信息添加成功</h3>
<%
} else {
%>
<h3>雇员信息添加失败</h3>
<%
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
</body>
</html>
