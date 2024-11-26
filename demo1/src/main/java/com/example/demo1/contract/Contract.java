package com.example.demo1.contract;

public class Contract {
    private int contractId;
    private int customerId;
    private int salespersonId;
    private String customerName; // 客户名称
    private double totalAmount;  // 总金额
    private String status;       // 合同状态
    private String createDate;   // 创建日期

    public Contract() {
        // 默认构造器
    }

    public Contract(int customerId, int salespersonId) {
        this.customerId = customerId;
        this.salespersonId = salespersonId;
    }

    // Getter 和 Setter 方法
    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getSalespersonId() {
        return salespersonId;
    }

    public void setSalespersonId(int salespersonId) {
        this.salespersonId = salespersonId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
