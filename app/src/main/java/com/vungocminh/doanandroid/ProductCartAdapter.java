package com.vungocminh.doanandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Map;

public class ProductCartAdapter extends RecyclerView.Adapter<ProductCartAdapter.ProductViewHolder> {

    private Context context;
    private Map<Product, Integer> products;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public ProductCartAdapter(Context context, Map<Product, Integer> products, OnItemClickListener listener) {
        this.context = context;
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = (Product) products.keySet().toArray()[position];
        Integer quantity = (Integer) products.values().toArray()[position];

        holder.productName.setText(product.getTitle());
        holder.productQuantity.setText(String.valueOf(quantity));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(product);
                }
            }
        });

        // Load product image using Glide
        Glide.with(context).load(product.getProduct_image()).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView productName;
        TextView productQuantity;
        ImageView productImage;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productQuantity = itemView.findViewById(R.id.product_quantity);
            productImage = itemView.findViewById(R.id.product_image);
        }
    }
}