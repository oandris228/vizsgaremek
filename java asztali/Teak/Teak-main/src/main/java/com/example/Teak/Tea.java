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

public class Tea {
    private static Connection connection;

    public static void setConnection(Connection c) {
        connection = c;
    }

    private int id;
    private String type;
    private String flavor;
    private int productId;

    public Tea(int id, String type, String flavor, int productId){
        this.id = id;
        this.type= type;
        this.flavor= flavor;
        this.productId= productId;
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
        if (connection == null ) throw new Exception("connection is not set in Tea");
        List<Tea> res = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select * from tea;"
        );
        while (resultSet.next()) {
            int id= resultSet.getInt("id");
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
        Statement statement = connection.createStatement();
        String sql = "insert into tea(id, type, flavor, productId) values('"+id+"','"+type+"','"+flavor+"','"+productId+"');";
        System.out.println(sql);
        statement.executeUpdate(sql);
        statement.close();
    }
}
