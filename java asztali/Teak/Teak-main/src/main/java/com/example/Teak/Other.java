package com.example.Teak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Other {
    private static Connection connection;

    public static void setConnection(Connection c) {
        connection = c;
    }

    private int id;
    private String description;
    private String img;
    private int productId;

    public Other(int id, String description, String img, int productId){
        this.id = id;
        this.description= description;
        this.img= img;
        this.productId= productId;
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
        if (connection == null ) throw new Exception("connection is not set in Other");
        List<Other> res = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select * from other;"
        );
        while (resultSet.next()) {
            int id= resultSet.getInt("id");
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
        Statement statement = connection.createStatement();
        String sql = "insert into other(id, description, img, productId) values('"+id+"','"+description+"','"+img+"','"+productId+"');";
        System.out.println(sql);
        statement.executeUpdate(sql);
        statement.close();
    }
}
