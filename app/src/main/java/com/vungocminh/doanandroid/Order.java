package com.vungocminh.doanandroid;

import java.util.Date;
import java.util.List;

public class Order {
    private List<Product> products;
    private List<Integer> quantities;
    private double totalPrice;
    private String address;
    private String userId;
    private Date orderDate;
    private String status;

    public Order() {
    }

    public Order(List<Product> products, List<Integer> quantities, double totalPrice, String address, String userId, Date orderDate, String status) {
        this.products = products;
        this.quantities = quantities;
        this.totalPrice = totalPrice;
        this.address = address;
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}