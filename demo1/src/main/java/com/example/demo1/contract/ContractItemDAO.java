package com.example.demo1.contract;

import com.example.demo1.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ContractItemDAO {

    /**
     * 保存合同商品信息
     * @param item 合同商品对象
     */
    public void saveContractItem(ContractItem item) {
        String sql = "INSERT INTO ContractItem (contract_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getContractId());
            stmt.setInt(2, item.getProductId());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getUnitPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Map<Integer, Integer> getRequiredProductQuantities() {
        Map<Integer, Integer> productQuantities = new HashMap<>();
        String sql = "SELECT product_id, quantity FROM ContractItem ci " +
                "JOIN Contract c ON ci.contract_id = c.contract_id " +
                "WHERE c.status IN ('未履行', '履行中')";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                int quantity = resultSet.getInt("quantity");
                productQuantities.merge(productId, quantity, Integer::sum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productQuantities;
    }
}