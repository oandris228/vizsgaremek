package com.example.Teak;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class Tea extends Product {
    private int teaId;

    private String type;
    private String flavor;
    private String color;

    public Tea(int teaId, int productId, String name, int price, String type, String flavor, String color) {
        super(productId, name, price);

        this.teaId = teaId;
        this.type = type;
        this.flavor = flavor;
        this.color = color;
    }

    public Tea(String name, int price, String type, String flavor, String color) {
        HttpResponse<JsonNode> res = Unirest.post(HelloApplication.getServerUrl() + "/products")
            .header("Content-Type", "application/json")
            .body("{" +
                "\"name\":\""      + name   + "\"," +
                "\"price\":"       + price  + ","   +
                "\"category\":\""  + "Tea"  + "\"," +
                "\"tea_type\":\""  + type   + "\"," +
                "\"tea_flavor\":\""+ flavor + "\"," +
                "\"tea_color\":\"" + color  + "\""  +
            "}").asJson();

        if (res.getStatus() != 201) {
            throw new RuntimeException("Failed to create tea in database: " + res.getStatus() + " " + res.getBody().getObject().toString());
        }

        JSONObject product = Unirest.get(HelloApplication.getServerUrl() + "/products/" + res.getBody().getObject().getInt("id")).asJson().getBody().getObject();

        this.productId = product.getInt("id");
        this.name = product.getString("name");
        this.price = product.getInt("price");
        this.teaId = product.getInt("tea_id");
        this.type = product.getJSONArray("Tea").optJSONObject(0).getString("type");
        this.flavor = product.getJSONArray("Tea").optJSONObject(0).getString("flavor");
        this.color = product.getJSONArray("Tea").optJSONObject(0).getString("color");
    }

    public int getTeaId() {
        return teaId;
    }

    public String getType() {
        return type;
    }

    public String getFlavor() {
        return flavor;
    }

    public String getColor() {
        return color;
    }
}