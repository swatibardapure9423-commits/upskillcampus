package com.autoparts.dao;

import com.autoparts.model.Product;
import java.util.List;

public class ProductTest {
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();

        List<Product> products = dao.getAllProducts();

        for (Product p : products) {
            System.out.println(
                p.getProductId() + "  " +
                p.getProductName() + "  ₹" +
                p.getPrice()
            );
        }
    }
}