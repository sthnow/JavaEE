<%@page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%--
  Created by IntelliJ IDEA.
  User: Wzz
  Date: 2018/6/19
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>列出user表里面的数据</title>
</head>
<body>
<%!

    public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/mldn?useSSL=false&serverTimezone=UTC";
    public static final String DBUSER = "root";
    public static final String DBPASS = "123456";
%>
<%
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
%>
<%
    try {
        Class.forName(DBDRIVER);    //加载驱动类
        conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);    //获取连接，参数：URL,USERID,PASSWORD
        String sql = "SELECT empno,ename,job,sal,hiredate FROM emp";   //给出sql语句
        pstmt = conn.prepareStatement(sql);    //实例化声明对象

        rs = pstmt.executeQuery();//执行sql语句
%>
<center>
    <table border="2" width="80%">
        <!--width表示占据页面的宽度-->
        <tr>
            <td>雇员编号</td>
            <td>雇员姓名</td>
            <td>雇员工作</td>
            <td>雇员薪水</td>
            <td>雇佣日期</td>
        </tr>
        <%
            while (rs.next()) {
                int empno = rs.getInt(1);
                String ename = rs.getString(2);
                String job = rs.getString(3);
                String sal = rs.getString(4);
                String date = rs.getString(5);
        %>
        <tr>
            <td><%=empno%>
            </td>
            <td><%=ename%>
            </td>
            <td><%=job%>
            </td>
            <td><%=sal%></td>
            <td><%=date%></td>
        </tr>
        <%
            }
        %>
    </table>
</center>
<% } catch (Exception e) {
    System.out.println(e);
} finally {
    rs.close();
    pstmt.close();
    conn.close();
}
%>


</body>
</html>
