package com.vungocminh.doanandroid;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

public class ProductListActivity extends BaseActivity {
    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ArrayList<Product> products;
    private DocumentSnapshot lastVisible;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#ffffff"));

        }
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.activity_product_list);
        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.product_recycler_view);
        products = new ArrayList<>();
        adapter = new ProductAdapter(this, products, new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                intent.putExtra("product", product);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
        SessionManager sessionManager = new SessionManager(this);

        ImageView logoutButton = findViewById(R.id.ic_profile);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo một hộp thoại xác nhận
                new AlertDialog.Builder(ProductListActivity.this)
                        .setTitle("Logout")
                        .setMessage("Do you really really really really want to logout?")
                        .setPositiveButton("Yes sir", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                sessionManager.logoutUser();
                                sessionManager.setLoginStatus(false);
                                Intent intent = new Intent(ProductListActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });


        fetchProducts(null);

        EditText searchInput = findViewById(R.id.search_input);
        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch(v.getText().toString());
                    return true;
                }
                return false;
            }
        });

        NestedScrollView nestedScrollView = findViewById(R.id.nestedScrollView);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) && !isLoading) {
                    fetchProducts(lastVisible);
                }
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.navigation_cart) {
                    Intent intent = new Intent(ProductListActivity.this, ShoppingCartActivity.class); // Thay CartActivity bằng tên lớp Activity thực tế của bạn
                    startActivity(intent);
                    return true;
                } else if (id == R.id.navigation_grid) {
                    // Handle navigation to cart
//                    Toast.makeText(ProductListActivity.this, "Grid selected", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProductListActivity.this, OrderHistoryActivity.class );
                    startActivity(intent);
                    return true;
                } else if (id == R.id.navigation_favorite) {
                    // Handle navigation to profile
                    Intent intent = new Intent(ProductListActivity.this, ChatActivity.class);
                    startActivity(intent);
                    return true;
                } else {

                    return false;
                }
            }
        });
    }
    private void performSearch(String query) {
        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }

    private void fetchProducts(DocumentSnapshot lastDoc) {
        isLoading = true;

        CollectionReference productsRef = db.collection("products");
        Query query;

        if (lastDoc == null) {
            query = productsRef.limit(30);
        } else {
            query = productsRef.startAfter(lastDoc).limit(30);
        }

        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@NonNull QuerySnapshot value, @NonNull FirebaseFirestoreException e) {
                isLoading = false;
                if (e != null) {
                    return;
                }
                if (value != null && !value.isEmpty()) {
                    if (lastDoc == null) {
                        products.clear();
                    }
                    for (QueryDocumentSnapshot doc : value) {
                        if (doc.exists()) {
                            Product product = doc.toObject(Product.class);
                            product.setId(doc.getId());
                            products.add(product);
                        }
                    }
                    lastVisible = value.getDocuments().get(value.size() - 1);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}