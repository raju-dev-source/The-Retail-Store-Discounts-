package com.retail.model;


public class Bill {
    private final double totalAmount;
    private final User user;

    public Bill(double totalAmount, User user) {
        this.totalAmount = totalAmount;
        this.user = user;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public User getUser() {
        return user;
    }
}
