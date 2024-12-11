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
  <title>合同签订成功</title>
  <script>
    function redirectToHomePage() {
      setTimeout(function() {
        // 定义返回的初始页面 URL，您需要替换为实际的初始页面路径
        window.location.href = "salesperson_dashboard.jsp"; // 或者其他初始页面
      }, 3000); // 3000 毫秒后跳转
    }
  </script>
</head>
<body onload="redirectToHomePage()">
<h2>合同签订成功！</h2>
<p>当前销售人员合同已成功签订。</p>
<p>您将于 3 秒后返回初始页面。</p>

<script>
  // 弹窗提示用户合同成功签订
  alert("合同签订成功，您将于 3 秒后返回初始页面。");
</script>
</body>
</html>
