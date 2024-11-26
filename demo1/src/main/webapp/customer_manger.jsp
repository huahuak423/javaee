<%--
  Created by IntelliJ IDEA.
  User: 刘华剑k423
  Date: 2024/11/26
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>客户管理</title>
</head>
<body>
<h2>客户管理</h2>
<form action="CustomerManagementServlet" method="post">
  <label>名称: </label><input type="text" name="name" required><br>
  <label>地址: </label><input type="text" name="address" required><br>
  <label>电话: </label><input type="text" name="phone" required><br>
  <label>邮箱: </label><input type="email" name="email" required><br>
  <button type="submit">添加客户</button>
</form>
<h3>客户列表</h3>
<table border="1">
  <tr>
    <th>客户编号</th>
    <th>名称</th>
    <th>地址</th>
    <th>电话</th>
    <th>邮箱</th>
  </tr>
  <c:forEach var="customer" items="${customers}">
    <tr>
      <td>${customer.customerId}</td>
      <td>${customer.name}</td>
      <td>${customer.address}</td>
      <td>${customer.phone}</td>
      <td>${customer.email}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
