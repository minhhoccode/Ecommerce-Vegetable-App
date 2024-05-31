package com.vungocminh.doanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends BaseActivity {

    private ListView ordersListView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        ordersListView = findViewById(R.id.order_list_view);
        orderList = new ArrayList<>();
        orderAdapter = new OrderAdapter(this, orderList);
        ordersListView.setAdapter(orderAdapter);

        // Kết nối đến Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ordersRef = database.getReference("orders");
        SessionManager sessionManager = new SessionManager(this);
        String phoneNumber = sessionManager.getUserDetails(SessionManager.KEY_PHONE_NUMBER);
        Query userOrdersQuery = ordersRef.orderByChild("userId").equalTo(phoneNumber);

        // Lấy dữ liệu từ Firebase với truy vấn
        userOrdersQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orderList.clear();
                for (DataSnapshot orderSnapshot : dataSnapshot.getChildren()) {
                    Order order = orderSnapshot.getValue(Order.class);
                    orderList.add(order);
                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý lỗi nếu cần
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_grid);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.navigation_home) {
                    Intent intent = new Intent( OrderHistoryActivity.this, ProductListActivity.class);
                    startActivity(intent);
                    return true;
                }else if (id == R.id.navigation_cart) {
                    Intent intent = new Intent(OrderHistoryActivity.this, ShoppingCartActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.navigation_favorite) {
                        Intent intent = new Intent( OrderHistoryActivity.this, ChatActivity.class);
                        startActivity(intent);
                        return true;
                    } else {
                        return false;
                    }
                }
            });
    }
}
