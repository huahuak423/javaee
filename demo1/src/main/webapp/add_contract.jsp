<%--
  Created by IntelliJ IDEA.
  User: 刘华剑k423
  Date: 2024/11/24
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<% response.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 或使用 jakarta.ee URI -->

<!DOCTYPE html>
<html>
<head>
  <title>新增合同</title>
  <script>
    function addProductRow() {
      const table = document.getElementById("product_table");
      const row = table.insertRow();
      row.innerHTML = `
        <td>
            <select name="product_id[]" required onchange="updateUnitPrice(this)">
                <c:forEach var="product" items="${products}">
                    <option value="${product.productId}" data-price="${product.price}">${product.name} - ${product.price}</option>
                </c:forEach>
            </select>
        </td>
        <td><input type="number" name="quantity[]" min="1" required></td>
        <td><input type="number" name="unit_price[]" step="0.01" min="2000" value="0" required></td>
        <td><button type="button" onclick="deleteRow(this)">删除</button></td>
    `;
    }

    function updateUnitPrice(select) {
      const selectedOption = select.options[select.selectedIndex];
      const unitPriceInput = select.closest('tr').querySelector('input[name="unit_price[]"]');
      const minPrice = selectedOption.getAttribute('data-price'); // 获取当前商品的价格

      // 更新单价输入框的最小值和默认值
      unitPriceInput.min = minPrice;
      unitPriceInput.value = minPrice; // 可选：将单价的初始值设为商品的价格
    }
  </script>
</head>
<body>
<h2>新增合同</h2>
<form action="AddContractServlet" method="post">
  <label>客户:</label>
  <select name="customer_id" required>
    <c:forEach var="customer" items="${customers}">
      <option value="${customer.customerId}">${customer.name}</option>
    </c:forEach>
  </select><br>

  <label>销售人员:</label>
  <select name="salesperson_id" required>
    <c:forEach var="salesperson" items="${salespersons}">
      <option value="${salesperson.id}">${salesperson.username}</option>
    </c:forEach>
  </select><br>

  <h3>商品信息</h3>
  <table id="product_table" border="1">
    <thead>
    <tr>
      <th>商品</th>
      <th>数量</th>
      <th>单价</th>

      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
  <button type="button" onclick="addProductRow()">添加商品</button><br>
  <button type="submit">提交合同</button>
</form>
</body>
</html>
