<%--
  Created by IntelliJ IDEA.
  User: 刘华剑k423
  Date: 2024/11/26
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>个人销售业绩</title>
</head>
<body>
<h2>个人销售业绩</h2>

<table border="1">
  <tr>
    <th>类型</th>
    <th>总金额（￥）</th>
  </tr>
  <tr>
    <td>已完成</td>
    <td>${completedSales}</td>
  </tr>
  <tr>
    <td>未履行和履行中</td>
    <td>${pendingSales}</td>
  </tr>
</table>

<a href="salesperson_dashboard.jsp">返回主页</a>
</body>
</html>
