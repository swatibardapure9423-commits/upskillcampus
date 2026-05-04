package com.autoparts.server;

import com.autoparts.dao.UserDAO;
import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class AppServer {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8085), 0);

        // REGISTER
        server.createContext("/register", exchange -> {
            try {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Content-Type", "text/plain");

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(exchange.getRequestBody())
                );

                String data = br.readLine();
                System.out.println("REGISTER DATA: " + data);

                String[] parts = data.split("&");
                String username = parts[0].split("=")[1];
                String password = parts[1].split("=")[1];

                UserDAO dao = new UserDAO();
                boolean success = dao.registerUser(username, password);

                String response;
                if (success) {
                    response = "Registration Successful";
                } else {
                    response = "Username already exists";
                }

                byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

                exchange.sendResponseHeaders(200, bytes.length);

                OutputStream os = exchange.getResponseBody();
                os.write(bytes);
                os.flush();
                os.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // LOGIN
        server.createContext("/login", exchange -> {
            try {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Content-Type", "text/plain");

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(exchange.getRequestBody())
                );

                String data = br.readLine();
                System.out.println("LOGIN DATA: " + data);

                String[] parts = data.split("&");
                String username = parts[0].split("=")[1];
                String password = parts[1].split("=")[1];

                UserDAO dao = new UserDAO();
                boolean success = dao.loginUser(username, password);

                String response;
                if (success) {
                    response = "LOGIN SUCCESS";
                } else {
                    response = "LOGIN FAILED";
                }

                byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

                exchange.sendResponseHeaders(200, bytes.length);

                OutputStream os = exchange.getResponseBody();
                os.write(bytes);
                os.flush();
                os.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // ORDER
        server.createContext("/order", exchange -> {
            try {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

                String response = "ORDER PLACED SUCCESSFULLY";
                byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

                exchange.sendResponseHeaders(200, bytes.length);

                OutputStream os = exchange.getResponseBody();
                os.write(bytes);
                os.flush();
                os.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        server.start();
        System.out.println("Server started at http://localhost:8085");
    }
}