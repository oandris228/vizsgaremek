package com.example.Teak;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static Connection connection;

    public static void setConnection(Connection c) {
        connection = c;
    }

    private int id;
    private int user_id;
    private String shipping_address;
    private String cart;

    public Order(int id, int user_id, String shipping_address, String cart){
        this.id = id;
        this.user_id= user_id;
        this.shipping_address= shipping_address;
        this.cart= cart;
    }
    public int getId() {
        return id;
    }

    public int getuser_id() {
        return user_id;
    }

    public String getshipping_address() {
        return shipping_address;
    }

    public String getcart() {
        return cart;
    }

    public static List<Order> getAll() throws Exception {
        if (connection == null ) throw new Exception("connection is not set in Order");
        List<Order> res = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select * from rendelesek;"
        );
        while (resultSet.next()) {
            int id= resultSet.getInt("id");
            int user_id = resultSet.getInt("user_id");
            String shipping_address = resultSet.getString("shipping_address").trim();
            String cart = resultSet.getString("cart").trim();
            res.add(new Order(id, user_id, shipping_address, cart));
        }
        resultSet.close();
        statement.close();
        return res;
    }

    public void upload() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "insert into rendelesek(id, user_id, shipping_address, cart) values('"+id+"','"+user_id+"','"+shipping_address+"','"+cart+"');";
        System.out.println(sql);
        statement.executeUpdate(sql);
        statement.close();
    }
}
