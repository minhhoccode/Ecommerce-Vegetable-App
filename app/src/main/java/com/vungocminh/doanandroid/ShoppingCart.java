package com.vungocminh.doanandroid;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> products; // Danh sách các sản phẩm trong giỏ hàng
    private List<Integer> quantities; // Danh sách số lượng tương ứng với mỗi sản phẩm

    public ShoppingCart() {
        products = new ArrayList<>();
        quantities = new ArrayList<>();
    }

    public int getProductQuantity(String productId) {
        int index = findProductIndex(productId);
        if (index != -1) {
            // Sản phẩm tồn tại trong giỏ hàng, trả về số lượng
            return quantities.get(index);
        } else {
            // Sản phẩm không tồn tại trong giỏ hàng, trả về 0
            return 0;
        }
    }

    public void addProduct(Product product, int quantity) {
        if (product != null) {
            int index = findProductIndex(product.getId());
            if (index != -1) {
                // Sản phẩm đã tồn tại trong giỏ hàng, cập nhật số lượng
                quantities.set(index, quantities.get(index) + quantity);
            } else {
                // Sản phẩm chưa tồn tại trong giỏ hàng, thêm mới
                products.add(product);
                quantities.add(quantity);
            }
        }
    }

    private int findProductIndex(String productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equalsIgnoreCase(productId)) {
                return i;
            }
        }
        return -1;
    }

    public void increaseQuantity(int index){
        quantities.set(index, quantities.get(index) + 1);
    }

    public void decreaseQuantity(int index){
        if(quantities.get(index) > 1){
            quantities.set(index, quantities.get(index) - 1);
        }
    }

    public Product findProduct(String productId) {
        for (Product product : products) {
            if (product.getId().equalsIgnoreCase(productId)) {
                return product;
            }
        }
        return null;
    }

    public void removeProduct(Product product) {
        int index = products.indexOf(product);
        if (index != -1) {
            products.remove(index);
            quantities.remove(index);
        }
    }

    public double getTotalPrice() {
        double total = 0;
        for (int i = 0; i < products.size(); i++) {
            total += products.get(i).getTotalPrice(quantities.get(i));
        }
//        làm tròn đến 2 số cuối
        total = Math.round(total * 100.0) / 100.0;
        return total;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void saveCart(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ShoppingCart", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this);
        editor.putString("Cart", json);
        editor.apply();
    }

    public static ShoppingCart loadCart(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ShoppingCart", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Cart", "");
        ShoppingCart shoppingCart = gson.fromJson(json, ShoppingCart.class);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
        }
        return shoppingCart;
    }

    public void clearCart() {
        products.clear();
        quantities.clear();
    }
}