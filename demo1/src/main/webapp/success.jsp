<%--
  Created by IntelliJ IDEA.
  User: 刘华剑k423
  Date: 2024/12/2
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>成功</title>
  <script>
    function redirectToHomePage() {
      setTimeout(function() {
        // 返回的初始页面 URL
        var userRole = '${userRole}'; // 获取实际的用户角色

        // 根据角色返回不同的主页
        if (userRole === '销售人员') {
          window.location.href = "salesperson_dashboard.jsp";
        } else if (userRole === '销售管理员') {
          window.location.href = "sales_admin_dashboard.jsp";
        } else if (userRole === '仓库管理员') {
          window.location.href = "warehouse_admin_dashboard.jsp";
        } else {
          window.location.href = "LoginServlet"; // 一般用户或其他角色的默认主页
        }
      }, 3000); // 3000 毫秒后跳转
    }
  </script>
</head>
<body onload="redirectToHomePage()">
<h2>当前操作成功！</h2>
<c:choose>
  <c:when test="${userRole == '销售人员'}">
    <p>当前销售人员合同已成功签订。</p>
  </c:when>
  <c:when test="${userRole == '销售管理员'}">
    <p>当前销售管理员的操作已成功。</p>
  </c:when>
  <c:when test="${userRole == '仓库管理员'}">
    <p>当前仓库管理员的操作已成功。</p>
  </c:when>
  <c:otherwise>
    <p>当前操作成功。</p>
  </c:otherwise>
</c:choose>
<p>您将于 3 秒后返回初始页面。</p>

<script>
  // 弹窗提示用户合同成功签订
  alert("当前操作成功，您将于 3 秒后返回初始页面。");
</script>
</body>
</html>
