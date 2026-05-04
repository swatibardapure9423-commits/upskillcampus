package com.autoparts.dao;

public class OrderTest {
    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();

        dao.placeOrder("Brake Pads", 2, 5000);
    }
}