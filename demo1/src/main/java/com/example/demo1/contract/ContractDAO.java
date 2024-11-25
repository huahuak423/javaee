package com.example.demo1.contract;

import com.example.demo1.DatabaseConnectionManager;

import java.sql.*;

public class ContractDAO {

    /**
     * 保存合同信息
     * @param contract 合同对象
     * @return 返回生成的合同ID
     */
    public int saveContract(Contract contract) {
        String sql = "INSERT INTO Contract (customer_id, salesperson_id, status, create_date) VALUES (?, ?, '未履行', NOW())";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, contract.getCustomerId());
            stmt.setInt(2, contract.getSalespersonId());
            stmt.executeUpdate();

            // 获取生成的合同ID
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 保存失败时返回 -1
    }
}