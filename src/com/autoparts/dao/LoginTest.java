package com.autoparts.dao;

public class LoginTest {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();

        boolean login = dao.validateUser("admin", "admin123");

        if (login) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Invalid Login");
        }
    }
}