package com.example.Teak;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/vizsgaremek"; // Database name
        String user = "root"; // Default XAMPP user
        String password = ""; // Default XAMPP password is empty

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

