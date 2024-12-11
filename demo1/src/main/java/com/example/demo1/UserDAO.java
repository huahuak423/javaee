package com.example.demo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    // 构造函数注入数据库连接
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // 验证用户登录信息
    public User validateUser(String username, String password, String role) throws SQLException {
        String sql = "SELECT * FROM [User] WHERE username = ? AND password = ? AND role = ? AND is_active = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password); // 如果密码加密，请在这里对 password 加密后再设置
            ps.setString(3, role);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = mapResultSetToUser(rs);
                    System.out.println("User authenticated: " + user); // 打印调试信息
                    return user;
                }
            }
        }
        System.out.println("User not found or inactive: username=" + username + ", role=" + role);
        return null; // 用户验证失败
    }

    // 插入新用户
    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO [User] (username, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword()); // 建议存储加密后的密码
            ps.setString(3, user.getRole());
            return ps.executeUpdate() > 0; // 返回插入是否成功
        }
    }
    // 在 UserDAO 中添加方法
    public List<User> getAllSalespersons() throws SQLException {
        List<User> salespersons = new ArrayList<>();
        String sql = "SELECT * FROM [User] WHERE role = '销售人员' AND is_active = 1";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                salespersons.add(mapResultSetToUser(rs));
            }
        }
        return salespersons;
    }

    // 查询所有用户
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM [User]";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        }
        return users;
    }

    // 根据 ID 查询用户
    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM [User] WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        }
        return null;
    }

    // 删除用户
    public boolean deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM [User] WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // 将 ResultSet 映射到 User 对象
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));

        // 处理可选字段
        int salespersonId = rs.getInt("salesperson_id");
        if (!rs.wasNull()) {
            user.setSalespersonId(salespersonId);
        }

        user.setActive(rs.getBoolean("is_active"));
        return user;
    }
}
