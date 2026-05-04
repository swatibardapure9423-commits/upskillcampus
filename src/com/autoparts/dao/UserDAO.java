package com.autoparts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserDAO {

    // REGISTER USER
    public boolean registerUser(String username, String password) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO USERS (username, password) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            int rows = ps.executeUpdate();

            con.close();

            return rows > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already exists");
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // LOGIN USER
    public boolean loginUser(String username, String password) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM USERS WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            boolean found = rs.next();

            con.close();

            return found;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // SHOW ALL USERS
    public void showUsers() {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM USERS";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Password: " + rs.getString("password"));
                System.out.println("-------------------");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}