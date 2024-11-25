package com.example.demo1.contract;

import com.example.demo1.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}