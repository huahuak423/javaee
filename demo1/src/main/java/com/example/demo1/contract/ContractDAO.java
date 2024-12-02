package com.example.demo1.contract;

import com.example.demo1.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                    return rs.getInt(1); // 返回合同ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 保存失败时返回 -1
    }

    /**
     * 根据销售人员ID查询合同
     * @param salespersonId 销售人员ID
     * @return 合同列表
     */
    public List<Contract> findContractsBySalespersonId(int salespersonId) {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT c.contract_id, cu.name AS customer_name, c.total_amount, c.status, c.create_date " +
                "FROM Contract c " +
                "JOIN Customer cu ON c.customer_id = cu.customer_id " +
                "WHERE c.salesperson_id = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salespersonId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Contract contract = new Contract();
                contract.setContractId(rs.getInt("contract_id"));
                contract.setCustomerName(rs.getString("customer_name"));
                contract.setTotalAmount(rs.getDouble("total_amount"));
                contract.setStatus(rs.getString("status"));
                contract.setCreateDate(rs.getString("create_date"));
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }
    public double getTotalSalesBySalespersonId(int salespersonId) {
        String sql = "SELECT SUM(total_amount) AS total_sales FROM Contract WHERE salesperson_id = ? AND status = '已完成'";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salespersonId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total_sales");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

}
