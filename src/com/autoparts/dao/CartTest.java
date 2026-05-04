package com.autoparts.dao;

import com.autoparts.model.Product;
import com.autoparts.model.CartItem;

public class CartTest {
    public static void main(String[] args) {
        Product product = new Product(1, "Brake Pads", 2500);
        CartItem cart = new CartItem(product, 2);

        System.out.println("Product: " + cart.getProduct().getProductName());
        System.out.println("Quantity: " + cart.getQuantity());
        System.out.println("Total: ₹" + cart.getTotalPrice());
    }
}