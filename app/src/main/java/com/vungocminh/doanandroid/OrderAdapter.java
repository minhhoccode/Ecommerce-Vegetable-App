package com.vungocminh.doanandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private List<Order> orders;
    private SimpleDateFormat dateFormat;

    public OrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        }

        Order order = orders.get(position);

        TextView orderDateTextView = convertView.findViewById(R.id.order_date);
        TextView totalPriceTextView = convertView.findViewById(R.id.total_price);
        TextView statusTextView = convertView.findViewById(R.id.status);
        TextView addressTextView = convertView.findViewById(R.id.address);
        LinearLayout productListLayout = convertView.findViewById(R.id.product_list);

        orderDateTextView.setText(dateFormat.format(order.getOrderDate()));
        totalPriceTextView.setText(String.format("$%.2f", order.getTotalPrice()));
        statusTextView.setText(order.getStatus());
        addressTextView.setText(order.getAddress());

        productListLayout.removeAllViews();
        List<Product> products = order.getProducts();
        List<Integer> quantities = order.getQuantities();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            Integer quantity = quantities.get(i);

            TextView productTextView = new TextView(context);
            productTextView.setText(String.format("%s x%d", product.getTitle(), quantity));
            productTextView.setTextSize(14);
            productListLayout.addView(productTextView);
        }

        return convertView;
    }
}
