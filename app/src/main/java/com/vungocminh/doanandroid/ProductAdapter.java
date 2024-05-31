package com.vungocminh.doanandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }
    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }
    public ProductAdapter(Context context, List<Product> productList, OnItemClickListener listener) {
        this.context = context;
        this.productList = productList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.title.setText(product.getTitle());
        holder.price1.setText(String.valueOf((int) product.getPrice()) + ".");
        holder.price2.setText(String.valueOf((int) ((product.getPrice() - (int) product.getPrice()) * 100)) + "$");
        holder.weight.setText(product.getWeight());
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
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView title, price1, price2, weight;
        ImageView productImage;

        public ProductViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.product_title);
            price1 = itemView.findViewById(R.id.price1);
            price2 = itemView.findViewById(R.id.price2);
            weight = itemView.findViewById(R.id.product_weight);
            productImage = itemView.findViewById(R.id.image_product_image);
        }
    }
}