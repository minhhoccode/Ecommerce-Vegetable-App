package com.vungocminh.doanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ProductDetailActivity extends BaseActivity {

    private ImageView productImage;
    private TextView productName;
    private TextView productPrice;
    private TextView productWeight;
    private TextView productDescription;
    private Button addToCartButton;
    private Button addToFavoriteButton;
    private Button decreaseQuantityButton;
    private Button increaseQuantityButton;
    private TextView quantityTextView;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail2);

        productImage = findViewById(R.id.product_image);
        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);
        productWeight = findViewById(R.id.product_weight);
        productDescription = findViewById(R.id.product_description);
        addToCartButton = findViewById(R.id.add_to_cart);
        addToFavoriteButton = findViewById(R.id.add_to_favorite);
        decreaseQuantityButton = findViewById(R.id.decrease_quantity);
        increaseQuantityButton = findViewById(R.id.increase_quantity);
        quantityTextView = findViewById(R.id.quantity);

        // Get product from intent
        product = (Product) getIntent().getSerializableExtra("product");

        if (product != null) {
            Glide.with(this).load(product.getProduct_image()).into(productImage);
            productName.setText(product.getTitle());
            productPrice.setText(String.valueOf(product.getPrice()));
            productWeight.setText(product.getWeight());
            productDescription.setText(product.getDescription());
        }

        decreaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(quantityTextView.getText().toString());
                if (quantity > 1) {
                    quantity--;
                    quantityTextView.setText(String.valueOf(quantity));
                }
            }
        });

        increaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(quantityTextView.getText().toString());
                quantity++;
                quantityTextView.setText(String.valueOf(quantity));
            }
        });

addToCartButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int quantity = Integer.parseInt(quantityTextView.getText().toString());
        // Load the ShoppingCart
        ShoppingCart shoppingCart = ShoppingCart.loadCart(ProductDetailActivity.this);
        // Add the product to the ShoppingCart
        shoppingCart.addProduct(product, quantity);
        // Save the ShoppingCart
        shoppingCart.saveCart(ProductDetailActivity.this);
        // Hiển thị thông báo đã thêm sản phẩm thành công
        Toast.makeText(ProductDetailActivity.this, "Đã thêm sản phẩm có ID: " + product.getId() + " vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
        // Chuyển hướng người dùng đến trang giỏ hàng
        Intent intent = new Intent(ProductDetailActivity.this, ShoppingCartActivity.class);
        ProductDetailActivity.this.startActivity(intent);
    }
});

//        addToFavoriteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addToFavorite(product);
//                // Chuyển hướng người dùng đến trang danh sách yêu thích
//                Intent intent = new Intent(ProductDetailActivity.this, FavoriteActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}