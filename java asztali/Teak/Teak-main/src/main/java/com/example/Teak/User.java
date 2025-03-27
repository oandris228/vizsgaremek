package com.example.Teak;

import com.example.Productk.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class User {
    private static Connection connection;

    public static void setConnection(Connection c) {
        connection = c;
    }

    private int id;
    private String username;
    private String  email;
    private String password;
    private String shipping_address;
    private byte admin;

    public User(int id, String username, String email, String password, String shipping_address, byte admin ){
        this.id = id;
        this.username= username;
        this.email= email;
        this.password= password;
        this.shipping_address= shipping_address;
        this.admin= admin;


    }
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String  getEmail() {
        return email;
    }

    public String getPassword() {
        return Product.password;
    }

    public String getShipping_address() {
        return shipping_address;
    }
    public byte getAdmin() {
        return admin;
    }


    public static List<User> getAll() throws Exception {
        if (connection == null ) throw new Exception("connection is not set in User");
        List<User> res = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select * from user;"
        );
        while (resultSet.next()) {
            int id= resultSet.getInt("id");
            String username = resultSet.getString("username").trim();
            String email = resultSet.getString("email").trim();
            String password = resultSet.getString("password").trim();
            String shipping_address = resultSet.getString("shipping_address").trim();
            byte admin = resultSet.getByte("admin");

            res.add(new User(id, username, email, password, shipping_address, admin));
        }
        resultSet.close();
        statement.close();
        return res;
    }

    public void upload() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "insert into product(id, username, email, password, shipping_address, admin) values('"+id+"','"+username+"','"+email+"','"+password+"','"+shipping_address+"','"+admin+");";
        System.out.println(sql);
        statement.executeUpdate(sql);
        statement.close();
    }
}
