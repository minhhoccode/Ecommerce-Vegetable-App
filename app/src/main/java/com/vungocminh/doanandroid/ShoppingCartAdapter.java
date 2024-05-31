package com.vungocminh.doanandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    private ShoppingCart shoppingCart;

    public ShoppingCartAdapter(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Product product = shoppingCart.getProducts().get(position);

        // Update the views in the ViewHolder with the product data
        holder.productName.setText(product.getTitle());
        holder.productQuantity.setText(String.valueOf(shoppingCart.getQuantities().get(position)));
        holder.productPrice.setText(String.valueOf(product.getPrice()));
//        add product_weight to ProductCartAdapter
        holder.productWeight.setText(String.valueOf(product.getWeight()));
//        load image
        Glide.with(holder.productImage.getContext())
                .load(product.getProduct_image())
                .into(holder.productImage);

        holder.increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCart.increaseQuantity(position);
                holder.productQuantity.setText(String.valueOf(shoppingCart.getQuantities().get(position)));
                shoppingCart.saveCart(v.getContext());
                notifyDataSetChanged();
                // Update total price
                ((TextView) ((Activity) v.getContext()).findViewById(R.id.total_price)).setText("Total: " + shoppingCart.getTotalPrice() + "$");
            }
        });

        holder.decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCart.decreaseQuantity(position);
                holder.productQuantity.setText(String.valueOf(shoppingCart.getQuantities().get(position)));
                shoppingCart.saveCart(v.getContext());
                notifyDataSetChanged();
                ((TextView) ((Activity) v.getContext()).findViewById(R.id.total_price)).setText("Total: " + shoppingCart.getTotalPrice() + "$");
            }
        });
    }


    @Override
    public int getItemCount() {
        return shoppingCart.getProducts().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productQuantity, productPrice, productWeight; // Add this line
        ImageView increaseButton, decreaseButton; // Change ImageButton to ImageView

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productQuantity = itemView.findViewById(R.id.product_quantity);
            productPrice = itemView.findViewById(R.id.product_price); // And this line
            increaseButton = itemView.findViewById(R.id.increase_button); // Change ImageButton to ImageView
            decreaseButton = itemView.findViewById(R.id.decrease_button); // Change ImageButton to ImageView
            productWeight = itemView.findViewById(R.id.product_weight); // Add this line
        }
    }
}