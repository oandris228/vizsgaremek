package com.example.Teak;

public abstract class Product {
    protected int productId;
    protected String name;
    protected int price;

    public Product(int productId, String name, int price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    protected Product() {}

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
