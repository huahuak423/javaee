package com.example.demo1.contract;


import java.util.Date;

public class Contract {
    private int contractId;
    private int customerId;
    private int salespersonId;
    private String customerName; // 客户名称
    private double totalAmount;  // 总金额
    private String status;       // 合同状态
    private Date createDate; // 创建日期
    private Date endDate; // 结束日期
    private String remarks; // 备注
    public Contract() {
        // 默认构造器
    }

    public Contract(int customerId, int salespersonId,String status,double totalAmount) {
        this.customerId = customerId;
        this.salespersonId = salespersonId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.createDate = new Date(); // 设置当前时间
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
    // 适合计算销售业绩的方法
    public double calculatePerformance(double commissionRate) {
        return this.totalAmount * commissionRate; // 销售业绩
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
