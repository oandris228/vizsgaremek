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

public class Ordertouser {
    private static Connection connection;

    public static void setConnection(Connection c) {
        connection = c;
    }

    private int A;
    private int B;

    public Ordertouser(int A, int B){
        this.A = A;
        this.B= B;
    }
    public int getA() {
        return A;
    }

    public int getB() {
        return B;
    }

    public static List<Ordertouser> getAll() throws Exception {
        if (connection == null ) throw new Exception("connection is not set in Ordertouser");
        List<Ordertouser> res = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select * from _ordertouser;"
        );
        while (resultSet.next()) {
            int A= resultSet.getInt("A");
            int B = resultSet.getInt("B");
            res.add(new Ordertouser(A, B));
        }
        resultSet.close();
        statement.close();
        return res;
    }

    public void upload() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "insert into _ordertouser(A, B) values('"+A+"','"+B+"');";
        System.out.println(sql);
        statement.executeUpdate(sql);
        statement.close();
    }
}
