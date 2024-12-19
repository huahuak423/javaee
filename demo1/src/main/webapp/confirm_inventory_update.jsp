<%--
  Created by IntelliJ IDEA.
  User: 刘华剑k423
  Date: 2024/12/17
  Time: 01:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>确认进货单更新</title>
</head>
<body>
<h2>确认所有进货单的更新</h2>

<form action="ConfirmInventoryUpdateServlet" method="post">
    <button type="submit">确认更新所有进货单</button>
</form>

<a href="warehouse_admin_dashboard.jsp">返回仓库管理主页</a>
</body>
</html>
