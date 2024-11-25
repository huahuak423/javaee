package com.example.demo1.contract;

import java.sql.*;

public class ContractDAO {
    public int saveContract(Contract contract) {
        String sql = "INSERT INTO Contract (customer_id, salesperson_id, status, create_date) VALUES (?, ?, '未履行', GETDATE())";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, contract.getCustomerId());
            stmt.setInt(2, contract.getSalespersonId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // 返回生成的合同ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 保存失败
    }
}
