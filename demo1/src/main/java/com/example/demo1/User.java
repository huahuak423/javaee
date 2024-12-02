package com.example.demo1;

public class User {
    private int id; // 用户唯一标识
    private String username; // 用户名
    private String password; // 密码
    private String role; // 用户角色：salesperson, sales_admin, warehouse_admin
    private int salespersonId; // 如果角色是销售人员，存储其关联的销售员 ID
    private boolean isActive; // 标记用户是否有效

    // 灵活的构造函数（允许部分字段）
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {}

    // Getters 和 Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("密码不能为空");
        }
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("角色不能为空");
        }
        this.role = role;
    }

    public int getSalespersonId() {
        return salespersonId;
    }

    public void setSalespersonId(int salespersonId) {
        this.salespersonId = salespersonId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
