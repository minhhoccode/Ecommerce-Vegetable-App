package com.vungocminh.doanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SearchResultsActivity extends BaseActivity {
    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ArrayList<Product> products;
    private DocumentSnapshot lastVisible;
    private boolean isLoading = false;
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.search_results_recycler_view);
        products = new ArrayList<>();
        adapter = new ProductAdapter(this, products
                , new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent = new Intent(SearchResultsActivity.this, ProductDetailActivity.class);
                intent.putExtra("product", product);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        if (intent != null) {
            query = intent.getStringExtra("query");
            performSearch(query);
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager != null && !isLoading && layoutManager.findLastVisibleItemPosition() >= products.size() - 1) {
                    fetchProducts(lastVisible);
                }
            }
        });
    }

    private void performSearch(String query) {
        products.clear();
        adapter.notifyDataSetChanged();
        fetchProducts(null);
    }

    private void fetchProducts(DocumentSnapshot lastDoc) {
        isLoading = true;
        CollectionReference productsRef = db.collection("products");
        Query queryRef;
        if (lastDoc == null) {
            queryRef = productsRef.whereGreaterThanOrEqualTo("title", query)
                    .whereLessThanOrEqualTo("title", query + "\uf8ff")
                    .limit(30);
        } else {
            queryRef = productsRef.whereGreaterThanOrEqualTo("title", query)
                    .whereLessThanOrEqualTo("title", query + "\uf8ff")
                    .startAfter(lastDoc)
                    .limit(30);
        }
        queryRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
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
