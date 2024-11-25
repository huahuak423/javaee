package com.example.demo1.contract;

public class Contract {
    private int customerId;
    private int salespersonId;

    public Contract(int customerId, int salespersonId) {
        this.customerId = customerId;
        this.salespersonId = salespersonId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getSalespersonId() {
        return salespersonId;
    }
}
