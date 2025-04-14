package com.example.Teak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private static Connection connection;

    public enum Category {
        TEA,
        OTHER
    }

    public static void setConnection(Connection c) {
        connection = c;
    }

    private int id;
    private String name;
    private int price;
    private Category category;
    private int other_id;
    private int tea_id;

    public Product(int id, String name, int price, Category category, int other_id, int tea_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.other_id = other_id;
        this.tea_id = tea_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public int getOther_id() {
        return other_id;
    }

    public int getTea_id() {
        return tea_id;
    }

    public static List<Product> getAll() throws Exception {
        if (connection == null) throw new Exception("Connection is not set in Product");
        List<Product> res = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM product;");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name").trim();
            int price = resultSet.getInt("price");
            Category category = Category.valueOf(resultSet.getString("category"));
            int other_id = resultSet.getInt("other_id");
            int tea_id = resultSet.getInt("tea_id");

            res.add(new Product(id, name, price, category, other_id, tea_id));
        }
        resultSet.close();
        statement.close();
        return res;
    }

    public void upload() throws SQLException {
        if (connection == null) {
            throw new SQLException("Connection is not set in Product");
        }
        String sql = "INSERT INTO product(id, name, price, category, other_id, tea_id) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setInt(3, price);
        preparedStatement.setString(4, category.name());
        preparedStatement.setInt(5, other_id);
        preparedStatement.setInt(6, tea_id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public static boolean exists(int productId) throws SQLException {
        String query = "SELECT COUNT(*) FROM product WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}