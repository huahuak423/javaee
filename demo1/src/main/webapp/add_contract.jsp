<%--
  Created by IntelliJ IDEA.
  User: 刘华剑k423
  Date: 2024/11/24
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>新增合同</title>
  <script>
    // 动态添加商品行
    function addProductRow() {
      const table = document.getElementById("product_table");
      const row = table.insertRow();
      row.innerHTML = `
        <td>
          <select name="product_id[]" required>
            <!-- 动态填充商品信息 -->
          </select>
        </td>
        <td><input type="number" name="quantity[]" min="1" required></td>
        <td><input type="number" name="unit_price[]" min="0" step="0.01" required></td>
        <td><button type="button" onclick="deleteRow(this)">删除</button></td>
      `;
    }

    // 删除商品行
    function deleteRow(button) {
      const row = button.parentElement.parentElement;
      row.parentElement.removeChild(row);
    }
  </script>
</head>
<body>
<h2>新增合同</h2>
<form action="AddContractServlet" method="post">
  <label for="customer_id">客户:</label>
  <select name="customer_id" id="customer_id">
    <!-- 动态填充客户信息 -->
  </select><br>
  <label for="salesperson_id">销售人员:</label>
  <select name="salesperson_id" id="salesperson_id">
    <!-- 动态填充销售人员信息 -->
  </select><br>
  <label for="total_amount">合同总金额:</label>
  <input type="text" id="total_amount" name="total_amount" required readonly><br>

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
    <!-- 动态填充商品行 -->
    </tbody>
  </table>
  <button type="button" onclick="addProductRow()">添加商品</button><br>

  <input type="submit" value="提交">
</form>
</body>
</html>
