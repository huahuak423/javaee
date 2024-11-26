<%--
  Created by IntelliJ IDEA.
  User: 刘华剑k423
  Date: 2024/11/26
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的合同</title>
</head>
<body>
<h2>我的合同</h2>
<table border="1">
    <tr>
        <th>合同编号</th>
        <th>客户名称</th>
        <th>总金额</th>
        <th>状态</th>
        <th>创建日期</th>
    </tr>
    <c:forEach var="contract" items="${contracts}">
        <tr>
            <td>${contract.contractId}</td>
            <td>${contract.customerName}</td>
            <td>${contract.totalAmount}</td>
            <td>${contract.status}</td>
            <td>${contract.createDate}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
