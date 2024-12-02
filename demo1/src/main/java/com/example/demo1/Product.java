package com.example.demo1;

import java.math.BigDecimal;

public class Product {
    private int productId; // 商品ID
    private String name;    // 商品名称
    private BigDecimal price; // 商品单价
    private int stock;      // 库存
    private int threshold;  // 库存阈值
    private String category; // 商品类别

    // 构造函数
    public Product(int productId, String name, BigDecimal price, int stock, int threshold, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.threshold = threshold;
        this.category = category;
    }

    // Getter 和 Setter 方法
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}