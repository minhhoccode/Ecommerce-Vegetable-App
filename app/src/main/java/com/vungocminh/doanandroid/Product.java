package com.vungocminh.doanandroid;

import java.io.Serializable;

public class Product implements Serializable {
    private String id;
    private String title;
    private double price;
    private String product_image;
    private int inventory_quantity;
    private String description;
    private int grams;
    private String weight;
    private String from;

    public Product() {
    }

    public Product(String title, double price, String product_image, int inventory_quantity, String description, int grams, String weight, String from) {
        this.title = title;
        this.price = price;
        this.product_image = product_image;
        this.inventory_quantity = inventory_quantity;
        this.description = description;
        this.grams = grams;
        this.weight = weight;
        this.from = from;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public int getInventory_quantity() {
        return inventory_quantity;
    }

    public void setInventory_quantity(int inventory_quantity) {
        this.inventory_quantity = inventory_quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotalPrice(int quantity) {
        return price * quantity;
    }
}