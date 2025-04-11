package com.example.Teak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static Connection connection;

    public static void setConnection(Connection c) {
        connection = c;
    }

    private int id;
    private int user_id;
    private String shipping_address;
    private String cart;

    public Order(int id, int user_id, String shipping_address, String cart) {
        this.id = id;
        this.user_id = user_id;
        this.shipping_address = shipping_address;
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public int getuser_id() {
        return user_id;
    }

    public String getshipping_address() {
        return shipping_address;
    }

    public String getcart() {
        return cart;
    }

    public static List<Order> getAll() throws Exception {
        if (connection == null) throw new Exception("Connection is not set in Order");
        List<Order> res = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM rendelesek;");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int user_id = resultSet.getInt("user_id");
            String shipping_address = resultSet.getString("shipping_address").trim();
            String cart = resultSet.getString("cart").trim();
            res.add(new Order(id, user_id, shipping_address, cart));
        }
        resultSet.close();
        statement.close();
        return res;
    }

    public void upload() throws SQLException {
        if (connection == null) {
            throw new SQLException("Connection is not set in Order");
        }
        String sql = "INSERT INTO rendelesek(id, user_id, shipping_address, cart) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, user_id);
        preparedStatement.setString(3, shipping_address);
        preparedStatement.setString(4, cart);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}