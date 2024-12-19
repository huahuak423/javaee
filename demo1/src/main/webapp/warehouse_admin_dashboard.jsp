<%--
  Created by IntelliJ IDEA.
  User: 刘华剑k423
  Date: 2024/11/26
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.example.demo1.User, com.example.demo1.PurchaseOrderDAO" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // 检查是否有未履行的进货单
    PurchaseOrderDAO purchaseOrderDAO = new PurchaseOrderDAO();
    boolean hasPendingOrders = !purchaseOrderDAO.getPendingOrders().isEmpty();
%>
<!DOCTYPE html>
<html>
<head>
    <title>仓库管理员页面</title>
    <script>
        // 页面加载时显示警告弹窗
        <% if (hasPendingOrders) { %>
        alert('当前还有未履行的进货单，请先完成进货！');
        <% } %>
    </script>
</head>
<body>
<h2>欢迎, <%= user.getUsername() %>!</h2>
<h2>功能选择</h2>
<% if (hasPendingOrders) { %>
<p style="color: red;">提示：当前有未履行的进货单，无法生成新的进货单。</p>
<% } else { %>
<a href="GeneratePurchaseOrderServlet">生成进货单</a> |
<% } %>
<a href="ViewInventoryServlet">查询商品库存</a> |
<form action="ConfirmInventoryUpdateServlet" method="post" style="display:inline;">
    <button type="submit">完成进货</button>
</form> |
<a href="LoginServlet">注销</a>
</body>
</html>

