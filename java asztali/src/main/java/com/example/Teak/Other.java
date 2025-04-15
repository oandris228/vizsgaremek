package com.example.Teak;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class Other extends  Product {
    private int otherId;
    private String description;
    private String img;

    public Other(int otherId, int productId, String name, int price, String description, String img) {
        super(productId, name, price);
        this.otherId = otherId;
        this.description = description;
        this.img = img;
    }

    public Other(String name, int price, String description, String img) {
        HttpResponse<JsonNode> res = Unirest.post(HelloApplication.getServerUrl() + "/products")
            .header("Content-Type", "application/json")
            .body("{" +
                "\"name\":\""               + name        + "\"," +
                "\"price\":"                + price       + ","   +
                "\"category\":\""           + "Other"     + "\"," +
                "\"others_description\":\"" + description +"\"," +
                "\"others_img\":\""         + img         +"\""+
            "}").asJson();

        if (res.getStatus() != 201) {
            throw new RuntimeException("Failed to create other in database: " + res.getStatus() + " " + res.getBody().getObject().toString());
        }

        JSONObject product = Unirest.get(HelloApplication.getServerUrl() + "/products/" + res.getBody().getObject().getInt("id")).asJson().getBody().getObject();

        this.productId = product.getInt("id");
        this.name = product.getString("name");
        this.price = product.getInt("price");
        this.otherId = product.getInt("other_id");
        this.description = product.getJSONArray("Other").optJSONObject(0).getString("description");
        this.img = product.getJSONArray("Other").optJSONObject(0).getString("img");
    }

    public int getOtherId() {
        return otherId;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }
}
