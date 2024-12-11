package com.example.demo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalespersonDAO {
    private Connection connection;

    // 构造函数注入数据库连接
    public SalespersonDAO(Connection connection) {
        this.connection = connection;
    }

    // 获取所有有效的销售人员
    public List<Salesperson> getAllSalespersons() {
        List<Salesperson> salespersons = new ArrayList<>();
        String sql = "SELECT * FROM Salesperson WHERE is_active = 1";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Salesperson salesperson = mapResultSetToSalesperson(rs);
                salespersons.add(salesperson);
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while fetching salespersons: " + e.getMessage());
            e.printStackTrace();
        }
        return salespersons;
    }

    // 将 ResultSet 映射到 Salesperson 对象
    private Salesperson mapResultSetToSalesperson(ResultSet rs) throws SQLException {
        Salesperson salesperson = new Salesperson();
        salesperson.setId(rs.getInt("salesperson_id"));
        salesperson.setUsername(rs.getString("name"));  // 根据实际字段名调整
        salesperson.setPhone(rs.getString("phone"));
        salesperson.setEmail(rs.getString("email"));
        salesperson.setActive(rs.getBoolean("is_active"));
        return salesperson;
    }
}