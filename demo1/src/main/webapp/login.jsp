<%--
  Created by IntelliJ IDEA.
  User: 刘华剑k423
  Date: 2024/11/24
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h2>登录页面</h2>

<c:if test="${not empty errorMsg}">
    <p style="color: red;">${errorMsg}</p>
</c:if>

<form action="LoginServlet" method="post">
    <label for="username">用户名:</label>
    <input type="text" id="username" name="username" required><br>
    <label for="password">密码:</label>
    <input type="password" id="password" name="password" required><br>
    <label for="role">角色:</label>
    <select name="role" id="role">
        <option value="销售人员">销售人员</option>
        <option value="销售管理员">销售管理员</option>
        <option value="仓库管理员">仓库管理员</option>
    </select><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
