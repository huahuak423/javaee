package com.example.demo1;
import com.example.demo1.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderDAO {
    // 创建新的进货单
    public boolean createPurchaseOrder(PurchaseOrder order) {
        String sql = "INSERT INTO PurchaseOrder (product_id, quantity, supplier, status, order_date) VALUES (?, ?, '东莞理工学院', '未履行', GETDATE())";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, order.getProductID());
            preparedStatement.setInt(2, order.getQuantity()); // 确保这个值合理
            System.out.println("Inserting PurchaseOrder: productId=" + order.getProductID() +
                    ", quantity=" + order.getQuantity());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // 可以在这里添加更详细的错误处理，例如打印 SQL 状态
        }
        return false;
    }
    // 获取所有未完成的进货单
    public List<PurchaseOrder> getPendingOrders() {
        List<PurchaseOrder> orders = new ArrayList<>();
        String sql = "SELECT * FROM PurchaseOrder WHERE status = '未履行'";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int order_id = resultSet.getInt("order_id");
                int productId = resultSet.getInt("product_id");
                int quantity = resultSet.getInt("quantity");
                String supplier = resultSet.getString("supplier");
                String status = resultSet.getString("status");
                orders.add(new PurchaseOrder(order_id,productId, quantity, supplier, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    // 更新进货单状态
    public boolean updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE PurchaseOrder SET status = ? WHERE order_id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, orderId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}