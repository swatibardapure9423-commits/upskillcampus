package com.autoparts.dao;

public class UserTest {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        dao.showUsers();

        // REGISTER
        boolean reg = dao.registerUser("swati", "12345");

        if (reg) {
            System.out.println("Registration Successful");
        } else {
            System.out.println("Registration Failed");
        }

        // LOGIN
        boolean login = dao.validateUser("swati", "12345");

        if (login) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Invalid Login");
        }
    }
}