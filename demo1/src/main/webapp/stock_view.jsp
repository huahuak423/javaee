<%--
  Created by IntelliJ IDEA.
  User: 刘华剑k423
  Date: 2024/11/24
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>商品库存</title>
</head>
<body>
<h2>商品库存信息</h2>

<table border="1">
    <tr>
        <th>商品ID</th>
        <th>名称</th>
        <th>单价</th>
        <th>库存</th>
        <th>库存阈值</th>
        <th>类别</th>
    </tr>
    <c:forEach var="product" items="${productList}">
        <tr>
            <td>${product.productId}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.stock}</td>
            <td>${product.threshold}</td>
            <td>${product.category}</td>
        </tr>
    </c:forEach>
</table>

<a href="warehouse_admin_dashboard.jsp">返回仓库管理主页</a>
</body>
</html>
