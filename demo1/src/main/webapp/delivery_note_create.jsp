<%--
  Created by IntelliJ IDEA.
  User: 刘华剑k423
  Date: 2024/11/24
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>生成发货单</title>
</head>
<body>
<h2>生成发货单</h2>
<form action="CreateDeliveryNoteServlet" method="post">
  <label for="contract_id">合同:</label>
  <select name="contract_id" id="contract_id">
    <!-- 动态填充合同信息 -->
  </select><br>
  <label for="product_id">商品:</label>
  <select name="product_id" id="product_id">
    <!-- 动态填充商品信息 -->
  </select><br>
  <label for="quantity">数量:</label>
  <input type="text" id="quantity" name="quantity" required><br>
  <input type="submit" value="提交">
</form>
</body>
</html>
