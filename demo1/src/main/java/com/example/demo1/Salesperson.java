package com.example.demo1;

public class Salesperson {
    private int id; // 唯一标识
    private String username; // 用户名
    private String phone; // 电话
    private String email; // 电子邮件
    private boolean isActive; // 是否有效

    // 灵活的构造函数（允许部分字段）
    public Salesperson(String username, String phone, String email) {
        this.username = username;
        this.phone = phone;
        this.email = email;
    }

    public Salesperson() {}

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
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Salesperson{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}