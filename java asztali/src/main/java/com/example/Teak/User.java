package com.example.Teak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private static Connection connection;

    public enum Role {
        ADMIN,
        USER
    }

    public static void setConnection(Connection c) {
        connection = c;
    }

    private int id;
    private String username;
    private String email;
    private String password;
    private String shipping_address;
    private Role role;

    public User(int id, String username, String email, String password, String shipping_address, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.shipping_address = shipping_address;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public Role getRole() {
        return role;
    }

    public static List<User> getAll() throws Exception {
        if (connection == null) throw new Exception("connection is not set in User");
        List<User> res = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user;");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username").trim();
            String email = resultSet.getString("email").trim();
            String password = resultSet.getString("password").trim();
            String shipping_address = resultSet.getString("shipping_address").trim();
            Role role = Role.valueOf(resultSet.getString("role").toUpperCase());

            res.add(new User(id, username, email, password, shipping_address, role));
        }
        resultSet.close();
        statement.close();
        return res;
    }

    public void upload() throws SQLException {
        if (connection == null) {
            throw new SQLException("Connection is not set in User");
        }
        String sql = "INSERT INTO product(id, username, email, password, shipping_address, role) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, password);
        preparedStatement.setString(5, shipping_address);
        preparedStatement.setString(6, role.name());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}