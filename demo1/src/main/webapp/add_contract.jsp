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
    const productOptions = `
    <c:forEach var="product" items="${products}">
      <option value="${product.productId}" data-price="${product.price}">${product.name}</option>
    </c:forEach>
  `;

    function addProductRow() {
      const table = document.getElementById("product_table");
      const row = table.insertRow();
      row.innerHTML = `
      <td>
        <select name="product_id[]" required onchange="updatePrice(this)">
          ${productOptions}
        </select>
      </td>
      <td><input type="number" name="quantity[]" min="1" required></td>
      <td><input type="number" name="unit_price[]" step="0.01" min="0" readonly></td>
      <td><button type="button" onclick="deleteRow(this)">删除</button></td>
    `;
    }

    function updatePrice(selectElement) {
      const selectedOption = selectElement.options[selectElement.selectedIndex];
      const price = selectedOption.getAttribute("data-price");
      const priceInput = selectElement.parentElement.nextElementSibling.nextElementSibling.firstElementChild;
      priceInput.value = price;
    }
  </script>
</head>
<body>
<h2>新增合同</h2>
<form action="AddContractServlet" method="post">
  <label>客户:</label>
  <select name="customer_id" required>
    <c:forEach var="customer" items="${customers}">
      <option value="${customer.id}">${customer.name}</option>
    </c:forEach>
  </select><br>

  <label>销售人员:</label>
  <select name="salesperson_id" required>
    <c:forEach var="salesperson" items="${salespersons}">
      <option value="${salesperson.id}">${salesperson.name}</option>
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
