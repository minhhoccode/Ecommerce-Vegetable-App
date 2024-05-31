// Generated by view binder compiler. Do not edit!
package com.vungocminh.doanandroid.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.vungocminh.doanandroid.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemOrderBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView address;

  @NonNull
  public final TextView orderDate;

  @NonNull
  public final LinearLayout productList;

  @NonNull
  public final TextView productListLabel;

  @NonNull
  public final TextView status;

  @NonNull
  public final TextView totalPrice;

  private ItemOrderBinding(@NonNull LinearLayout rootView, @NonNull TextView address,
      @NonNull TextView orderDate, @NonNull LinearLayout productList,
      @NonNull TextView productListLabel, @NonNull TextView status, @NonNull TextView totalPrice) {
    this.rootView = rootView;
    this.address = address;
    this.orderDate = orderDate;
    this.productList = productList;
    this.productListLabel = productListLabel;
    this.status = status;
    this.totalPrice = totalPrice;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemOrderBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemOrderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_order, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemOrderBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.address;
      TextView address = ViewBindings.findChildViewById(rootView, id);
      if (address == null) {
        break missingId;
      }

      id = R.id.order_date;
      TextView orderDate = ViewBindings.findChildViewById(rootView, id);
      if (orderDate == null) {
        break missingId;
      }

      id = R.id.product_list;
      LinearLayout productList = ViewBindings.findChildViewById(rootView, id);
      if (productList == null) {
        break missingId;
      }

      id = R.id.product_list_label;
      TextView productListLabel = ViewBindings.findChildViewById(rootView, id);
      if (productListLabel == null) {
        break missingId;
      }

      id = R.id.status;
      TextView status = ViewBindings.findChildViewById(rootView, id);
      if (status == null) {
        break missingId;
      }

      id = R.id.total_price;
      TextView totalPrice = ViewBindings.findChildViewById(rootView, id);
      if (totalPrice == null) {
        break missingId;
      }

      return new ItemOrderBinding((LinearLayout) rootView, address, orderDate, productList,
          productListLabel, status, totalPrice);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}