<%--
  Created by IntelliJ IDEA.
  User: 刘华剑k423
  Date: 2024/11/26
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.example.demo1.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>销售管理员页面</title>
</head>
<body>
<h2>欢迎, <%= user.getUsername() %>!</h2>
<h2>功能选择</h2>
<a href="CustomerManagementServlet">新增客户</a> |
<a href="ModifyContractServlet">修改合同</a> |
<a href="ViewSalesPerformanceServlet"></a>
<a href="LoginServlet">注销</a>

</body>
</html>
