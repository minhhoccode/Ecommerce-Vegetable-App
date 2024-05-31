package com.vungocminh.doanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends BaseActivity {
    private EditText textYourAddress;
    private Button buttonConfirm;
    private SessionManager sessionManager;
    private ShoppingCart shoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        textYourAddress = findViewById(R.id.text_your_address);
        sessionManager = new SessionManager(this);
        shoppingCart = ShoppingCart.loadCart(this);

        String userAddress = sessionManager.getUserDetails(SessionManager.KEY_ADDRESS);
        if (userAddress != null) {
            textYourAddress.setText(userAddress);
        }
        buttonConfirm = findViewById(R.id.button_confirm);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = textYourAddress.getText().toString().trim();

                if (address.isEmpty()) {
                    Toast.makeText(PaymentActivity.this, "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show();
                } else {
                    // Tạo một đơn hàng mới
                    Order order = new Order(
                            shoppingCart.getProducts(),
                            shoppingCart.getQuantities(),
                            shoppingCart.getTotalPrice(),
                            address,
                            sessionManager.getUserDetails(SessionManager.KEY_PHONE_NUMBER),
                            new Date(),
                            "Pending"
                    );

                    // Đẩy đơn hàng lên Firebase
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("orders");
                    String orderId = databaseReference.push().getKey();
                    if (orderId != null) {
                        databaseReference.child(orderId).setValue(order)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(PaymentActivity.this, "Order placed successfully", Toast.LENGTH_SHORT).show();
                                    shoppingCart.clearCart();
                                    Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                })
                                .addOnFailureListener(e -> Toast.makeText(PaymentActivity.this, "Failed to place order", Toast.LENGTH_SHORT).show());
                    }

                    // Update the user's address in the session
                    sessionManager.updateAddress(address);
                    Intent intent = new Intent(PaymentActivity.this, ProductListActivity.class);
                    startActivity(intent);
//                    Clear Cart
                    shoppingCart.clearCart();
                    shoppingCart.saveCart(PaymentActivity.this);
                }
            }
        });
    }
}
