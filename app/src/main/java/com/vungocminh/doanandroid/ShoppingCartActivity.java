package com.vungocminh.doanandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShoppingCartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ShoppingCartAdapter adapter;
    private ShoppingCart shoppingCart;
    private ImageView backButton;
    private Button checkoutButton;

    private TextView total_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#0A4731"));

        }
        setContentView(R.layout.activity_shopping_cart);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.cart_recycler_view);

        // Initialize the ShoppingCart
        shoppingCart = ShoppingCart.loadCart(this);

        Product product = (Product) getIntent().getSerializableExtra("product");
        int quantity = getIntent().getIntExtra("quantity", 1);

        shoppingCart.addProduct(product, quantity);

        adapter = new ShoppingCartAdapter(shoppingCart);

        shoppingCart.saveCart(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCartActivity.this, ProductListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        total_price = findViewById(R.id.total_price);
        total_price.setText("Total: " + shoppingCart.getTotalPrice() + "$");

        checkoutButton = findViewById(R.id.checkout_button);

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCartActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_cart);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.navigation_home) {
                    Intent intent = new Intent(ShoppingCartActivity.this, ProductListActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.navigation_grid) {
                    // Handle navigation to cart
                    Intent intent = new Intent(ShoppingCartActivity.this, OrderHistoryActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.navigation_favorite) {
                    // Handle navigation to profile
                    Intent intent = new Intent(ShoppingCartActivity.this, ChatActivity.class);
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });

    }

}