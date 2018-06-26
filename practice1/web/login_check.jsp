<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%--
  Created by IntelliJ IDEA.
  User: Wzz
  Date: 2018/6/21
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%!
    //准备四大参数
    public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";   //驱动的地址
    public static final String DBURL = "jdbc:mysql://localhost:3306/mldn?useSSL=false&serverTimezone=UTC";   //数据库的地址
    public static final String DBUSER = "root"; //用户名
    public static final String DBPASSWORD = "123456";   //密码
%>
<%
    /**
     * 初始化三大参数
     * 1.初始化连接
     * 2.初始化声明
     * 3.初始化结果集
     */
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String name = null;
    Boolean flag = false;
%>
<%
    try { //加载驱动类
        Class.forName(DBDRIVER);
        conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
        String sql = "SELECT name FROM user WHERE userid=? AND password=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, request.getParameter("id"));
        pstmt.setString(2, request.getParameter("password"));
        rs = pstmt.executeQuery();
        if (rs.next()) {
            //如果能查到，则登陆成功
            name = rs.getString(1); //取出真实姓名
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
    <jsp:forward page="login_success.jsp">
        <jsp:param name="uname" value="<%=name%>"></jsp:param>
    </jsp:forward>
<%
    }else{
%>
<jsp:forward page="login_failed.html"></jsp:forward>
<%
    }
%>
</body>
</html>
