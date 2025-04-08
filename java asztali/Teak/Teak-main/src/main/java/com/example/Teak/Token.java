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

public class Token {
    private static Connection connection;

    public static void setConnection(Connection c) {
        connection = c;
    }

    private String token;
    private int userId;

    public Token(String token, int userId){
        this.token= token;
        this.userId= userId;
    }

    public String getToken() {
        return token;
    }

    public int getUserId() {
        return userId;
    }

    public static List<Token> getAll() throws Exception {
        if (connection == null ) throw new Exception("connection is not set in Token");
        List<Token> res = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select * from token;"
        );
        while (resultSet.next()) {
            String token = resultSet.getString("token").trim();
            int userId = resultSet.getInt("userId");
            res.add(new Token(token, userId));
        }
        resultSet.close();
        statement.close();
        return res;
    }

    public void upload() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "insert into token(token, userId) values('"+token+"','"+userId+"');";
        System.out.println(sql);
        statement.executeUpdate(sql);
        statement.close();
    }
}
