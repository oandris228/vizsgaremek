package com.example.Teak;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class Commission {
    enum State {
        Active,
        Processed,
        Completed
    }

    private int commissionId;
    private int userId;
    private int productId;
    private int itemId;
    private int quantity;
    private String productName;
    private String shippingAddress;
    private State state;
    private String extratext;

    public  Commission(int commissionId, int userId, int productId, int itemId, int quantity, String productName, String shippingAddress, State state, String extratext) {
        this.commissionId = commissionId;
        this.userId = userId;
        this.productId = productId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.productName = productName;
        this.shippingAddress = shippingAddress;
        this.state = state;
        this.extratext = extratext;
    }

    public Commission(int userId, int productId, int quantity, String extratext) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.extratext = extratext;

        HttpResponse<JsonNode> user = Unirest.get(HelloApplication.getServerUrl() + "/users/" + userId).asJson();
        if (user.getStatus() != 200) {
            throw new RuntimeException("Failed to get user from database: " + user.getStatus() + " " + user.getBody().getObject().toString());
        }
        shippingAddress = user.getBody().getObject().getString("shipping_address");

        HttpResponse<JsonNode> product = Unirest.get(HelloApplication.getServerUrl() + "/products/" + productId).asJson();
        if(product.getStatus() != 200) {
            throw new RuntimeException("Failed to get product from database: " + product.getStatus() + " " + product.getBody().getObject().toString());
        }
        productName = product.getBody().getObject().getString("name");

        state = State.Active;
        String stateString = state.toString();

        HttpResponse<JsonNode> res = Unirest.post(HelloApplication.getServerUrl() + "/items")
            .header("Content-Type", "application/json")
            .body("{" +
                    "\"productId\":" +                      productId       + "," +
                    "\"quantity\":" +                       quantity        + "," +
                    "\"productName\":\"" +                  productName     + "\"," +
                    "\"commission_shipping_address\":\"" +  shippingAddress + "\"," +
                    "\"commission_user_id\":"   +           userId          + "," +
                    "\"commission_commissionState\":\""+    stateString     + "\"," +
                    "\"commission_extratext\":\"" +         extratext       + "\"" +
            "}").asJson();
        if (res.getStatus() != 201) {
            throw new RuntimeException("Failed to create commission in database: " + res.getStatus() + " " + res.getBody().getObject().toString());
        }
        itemId = res.getBody().getObject().getInt("id");
        JSONObject item = Unirest.get(HelloApplication.getServerUrl() + "/items/" + itemId).asJson().getBody().getObject();
        commissionId = item.getInt("commissionId");
    }

    public int getCommissionId() {
        return commissionId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public State getState() {
        return state;
    }

    public String getExtratext() {
        return extratext;
    }
}
