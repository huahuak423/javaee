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
        String sql = "INSERT INTO Contract (customer_id, salesperson_id, status, create_date) VALUES (?, ?, '未履行', GETDATE())";
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
            System.err.println("Error occurred while saving contract: " + e.getMessage());
            e.printStackTrace();
        }

        return -1; // 保存失败时返回 -1
    }
    // 在 ContractDAO 类中添加更新合同总金额的方法
    public void updateContractTotalAmount(Contract contract) {
        String sql = "UPDATE Contract SET total_amount = ? WHERE contract_id = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, contract.getTotalAmount());
            stmt.setInt(2, contract.getContractId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error occurred while saving contract: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 根据销售人员ID查询合同
     * @param salespersonId 销售人员ID
     * @return 合同列表
     */
    public List<Contract> findContractsBySalespersonId(int salespersonId) {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT * FROM Contract WHERE salesperson_id = ?"; // 假设字段名称是 salesperson_id
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, salespersonId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Contract contract = new Contract();
                contract.setContractId(rs.getInt("contract_id"));
                contract.setCustomerId(rs.getInt("customer_id"));
                contract.setTotalAmount(rs.getDouble("total_amount"));
                contract.setStatus(rs.getString("status"));
                contract.setCreateDate(rs.getDate("create_date"));
                contracts.add(contract);
            }

        } catch (SQLException e) {
            // 可以选择记录到日志文件或抛出自定义异常
            System.err.println("Error occurred while fetching contracts: " + e.getMessage());
            throw new RuntimeException("Database access error.", e);
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
            System.err.println("Error occurred while saving contract: " + e.getMessage());
            e.printStackTrace();
        }

        return 0.0;
    }
    // 获取未履行和履行中合同的总销售额
    public double getPendingSalesBySalespersonId(int salespersonId) {
        String sql = "SELECT SUM(total_amount) AS total_sales FROM Contract WHERE salesperson_id = ? AND status IN ('未履行', '履行中')";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, salespersonId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total_sales");
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while saving contract: " + e.getMessage());
            e.printStackTrace();
        }

        return 0.0;
    }
}
