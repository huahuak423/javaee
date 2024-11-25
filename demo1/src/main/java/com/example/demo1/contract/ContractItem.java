package com.example.demo1.contract;

public class ContractItem {
    private int contractId;
    private int productId;
    private int quantity;
    private double unitPrice;

    public ContractItem(int contractId, int productId, int quantity, double unitPrice) {
        this.contractId = contractId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getContractId() {
        return contractId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}
