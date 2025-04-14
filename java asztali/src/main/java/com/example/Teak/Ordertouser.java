package com.example.Teak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ordertouser {
    private static Connection connection;

    public static void setConnection(Connection c) {
        connection = c;
    }

    private int A;
    private int B;

    public Ordertouser(int A, int B) {
        this.A = A;
        this.B = B;
    }

    public int getA() {
        return A;
    }

    public int getB() {
        return B;
    }

    public static List<Ordertouser> getAll() throws Exception {
        if (connection == null) throw new Exception("Connection is not set in Ordertouser");
        List<Ordertouser> res = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM _ordertouser;");
        while (resultSet.next()) {
            int A = resultSet.getInt("A");
            int B = resultSet.getInt("B");
            res.add(new Ordertouser(A, B));
        }
        resultSet.close();
        statement.close();
        return res;
    }

    public void upload() throws SQLException {
        if (connection == null) {
            throw new SQLException("Connection is not set in Ordertouser");
        }
        String sql = "INSERT INTO _ordertouser(A, B) VALUES(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, A);
        preparedStatement.setInt(2, B);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}