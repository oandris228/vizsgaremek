package com.example.Teak;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class User {
    enum Role {
        Admin,
        User
    }

    private int userId;
    private String username;
    private String email;
    private String password;
    private String shippingAddress;
    private Role role;

    public User(int userId, String username, String email, String password, String shippingAddress, Role role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.shippingAddress = shippingAddress;
        this.role = role;
    }

    public User(String username, String email, String password, String shippingAddress, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.shippingAddress = shippingAddress;
        this.role = role;

        HttpResponse<JsonNode> res = Unirest.post(HelloApplication.getServerUrl() + "/users")
            .header("Content-Type", "application/json")
            .body("{" +
                "\"username\":\""         + username        + "\"," +
                "\"email\":\""            + email           + "\"," +
                "\"password\":\""         + password        + "\"," +
                "\"shipping_address\":\"" + shippingAddress + "\"," +
                "\"role\":\""             + role.toString() + "\""  +
            "}").asJson();

        if (res.getStatus() != 201) {
            throw new RuntimeException("Failed to create user in database: " + res.getStatus() + " " + res.getBody().getObject().toString());
        }

        userId = res.getBody().getObject().getInt("id");
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public Role getRole() {
        return role;
    }
}
