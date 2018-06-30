<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Wzz
  Date: 2018/6/30
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询数据库</title>
</head>
<body>
<%!
    /**
     * 单线程
     * 功能
     *  通过用户名查询数据库，并返回结果
     */
%>
<%!
    //声明全局变量,方法,类
    //准备四大参数
    public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/mldn?userSSL=false&serverTimezone=UTC";
    public static final String DBUSER = "root";
    public static final String DBPASSWORD = "123456";
%>
<%
    //声明变量
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String name = null;
    Boolean flag = false;
%>
<%
    try {
        //加载驱动类
        Class.forName(DBDRIVER);
        conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
        String sql = "SELECT name FROM user WHERE userid = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, request.getParameter("id"));
        rs = pstmt.executeQuery();
        if (rs.next()) {
            flag = true;
        }
    } catch (Exception e) {
        System.out.println(e);
    } finally {
        rs.close();
        pstmt.close();
        conn.close();
    }
%>
<%
    if(flag){
%>
<!--这里似乎不能隔行,如果隔行不传递jsp:param则会报错-->
    <jsp:forward page="data_find_succ.jsp"></jsp:forward>
<%
    }else {
%>
<jsp:forward page="data_find_failed.jsp"></jsp:forward>
<%
    }
%>
</body>
</html>
