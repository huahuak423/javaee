<%--
  Created by IntelliJ IDEA.
  User: 刘华剑k423
  Date: 2024/12/16
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>确认生成进货单</title>
</head>
<body>
<h2>确认生成进货单</h2>

<c:choose>
  <c:when test="${empty purchaseOrderDetails}">
    <p>当前库存充足，无需补货。</p>
  </c:when>
  <c:otherwise>
    <table border="1">
      <tr>
        <th>商品ID</th>
        <th>补充数量</th>
      </tr>
      <c:forEach var="entry" items="${purchaseOrderDetails}">
        <tr>
          <td>${entry.key}</td>
          <td>${entry.value}</td>
        </tr>
      </c:forEach>
    </table>

    <form action="ConfirmPurchaseOrderServlet" method="post">
      <c:forEach var="entry" items="${purchaseOrderDetails}">
        <input type="hidden" name="orderDetails" value="${entry.key}=${entry.value}"/>
      </c:forEach>
      <button type="submit">确认生成进货单</button>
    </form>
  </c:otherwise>
</c:choose>

<a href="warehouse_admin_dashboard.jsp">返回仓库管理主页</a>
</body>
</html>

