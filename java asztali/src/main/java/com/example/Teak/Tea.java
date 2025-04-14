package com.example.Teak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Tea {
    private static Connection connection;

    public static void setConnection(Connection c) {
        connection = c;
    }

    private int id;
    private String type;
    private String flavor;
    private int productId;

    public Tea(int id, String type, String flavor, int productId) {
        this.id = id;
        this.type = type;
        this.flavor = flavor;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getFlavor() {
        return flavor;
    }

    public int getProductId() {
        return productId;
    }

    public static List<Tea> getAll() throws Exception {
        if (connection == null) throw new Exception("Connection is not set in Tea");
        List<Tea> res = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM tea;");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String type = resultSet.getString("type").trim();
            String flavor = resultSet.getString("flavor").trim();
            int productId = resultSet.getInt("productId");
            res.add(new Tea(id, type, flavor, productId));
        }
        resultSet.close();
        statement.close();
        return res;
    }

    public void upload() throws SQLException {
        if (connection == null) {
            throw new SQLException("Connection is not set in Tea");
        }
        String sql = "INSERT INTO tea(id, type, flavor, productId) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, type);
        preparedStatement.setString(3, flavor);
        preparedStatement.setInt(4, productId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}