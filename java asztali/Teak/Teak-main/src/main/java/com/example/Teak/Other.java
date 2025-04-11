package com.example.Teak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Other {
    private static Connection connection;

    public static void setConnection(Connection c) {
        connection = c;
    }

    private int id;
    private String description;
    private String img;
    private int productId;

    public Other(int id, String description, String img, int productId) {
        this.id = id;
        this.description = description;
        this.img = img;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return description;
    }

    public String getFlavor() {
        return img;
    }

    public int getProductId() {
        return productId;
    }

    public static List<Other> getAll() throws Exception {
        if (connection == null) throw new Exception("Connection is not set in Other");
        List<Other> res = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM other;");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String description = resultSet.getString("description").trim();
            String img = resultSet.getString("img").trim();
            int productId = resultSet.getInt("productId");
            res.add(new Other(id, description, img, productId));
        }
        resultSet.close();
        statement.close();
        return res;
    }

    public void upload() throws SQLException {
        if (connection == null) {
            throw new SQLException("Connection is not set in Other");
        }
        String sql = "INSERT INTO other(id, description, img, productId) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, img);
        preparedStatement.setInt(4, productId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}