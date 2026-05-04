package com.autoparts.model;

public class Order {
    private int orderId;
    private String productName;
    private int quantity;
    private double totalPrice;

    public Order(int orderId, String productName, int quantity, double totalPrice) {
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}