<%--
  Created by IntelliJ IDEA.
  User: 刘华剑k423
  Date: 2024/11/24
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>库存查询</title>
</head>
<body>
<h2>商品库存查询</h2>
<table border="1">
    <tr>
        <th>商品编号</th>
        <th>商品名称</th>
        <th>库存量</th>
        <th>阈值</th>
    </tr>
    <!-- 使用 JSTL 或脚本动态填充商品库存信息 -->
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.product_id}</td>
            <td>${product.name}</td>
            <td>${product.stock}</td>
            <td>${product.threshold}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
