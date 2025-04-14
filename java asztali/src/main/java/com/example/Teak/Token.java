package com.example.Teak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Token {
    private static Connection connection;

    public static void setConnection(Connection c) {
        connection = c;
    }

    private String token;
    private int userId;

    public Token(String token, int userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public int getUserId() {
        return userId;
    }

    public static List<Token> getAll() throws Exception {
        if (connection == null) throw new Exception("Connection is not set in Token");
        List<Token> res = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM token;");
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
        if (connection == null) {
            throw new SQLException("Connection is not set in Token");
        }
        String sql = "INSERT INTO token(token, userId) VALUES(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, token);
        preparedStatement.setInt(2, userId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}