package com.example.Teak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ordertoproduct {
    private static Connection connection;

    public static void setConnection(Connection c) {
        connection = c;
    }

    private int A;
    private int B;

    public Ordertoproduct(int A, int B) {
        this.A = A;
        this.B = B;
    }

    public int getA() {
        return A;
    }

    public int getB() {
        return B;
    }

    public static List<Ordertoproduct> getAll() throws Exception {
        if (connection == null) throw new Exception("Connection is not set in Ordertoproduct");
        List<Ordertoproduct> res = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM _ordertoproduct;");
        while (resultSet.next()) {
            int A = resultSet.getInt("A");
            int B = resultSet.getInt("B");
            res.add(new Ordertoproduct(A, B));
        }
        resultSet.close();
        statement.close();
        return res;
    }

    public void upload() throws SQLException {
        if (connection == null) {
            throw new SQLException("Connection is not set in Ordertoproduct");
        }
        String sql = "INSERT INTO _ordertoproduct(A, B) VALUES(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, A);
        preparedStatement.setInt(2, B);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}