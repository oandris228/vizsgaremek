package com.example.Teak;

import java.util.ArrayList;
import java.util.List;

public class Tea {
    private int teaId;
    private int productId;

    private String name;
    private int price;

    private String type;
    private String flavor;

    public Tea(int teaId, int productId, String name, int price, String type, String flavor) {
        this.teaId = teaId;
        this.productId = productId;

        this.name = name;
        this.price = price;

        this.type = type;
        this.flavor = flavor;
    }

    public int getTeaId() {
        return teaId;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getFlavor() {
        return flavor;
    }
}