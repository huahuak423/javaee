package com.example.demo1;

import java.util.Date;

public class PurchaseOrder {
    private int orderID;
    private Date orderDate;
    private int ProductID;
    private int quantity;
    private String status; // 合同状态
    private String supplier; // 合同状态


    public PurchaseOrder(int orderID,int productID, int quantity , String supplier,String status) {
        this.orderID = orderID;
        this.orderDate = new Date(); // 设置当前时间
        this.ProductID = productID;
        this.quantity = quantity;
        this.supplier = supplier;
        this.status = status;
    }
    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public int getProductID() {
        return ProductID;
    }
    public void setProductID(int productID) {
        this.ProductID = productID;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getSupplier() {
        return supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
