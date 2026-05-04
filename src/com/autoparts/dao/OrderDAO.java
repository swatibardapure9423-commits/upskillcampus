package com.autoparts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderDAO {

    public void placeOrder(String productName, int quantity, double totalPrice) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO orders VALUES (order_seq.NEXTVAL, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, productName);
            ps.setInt(2, quantity);
            ps.setDouble(3, totalPrice);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Order Placed Successfully");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}