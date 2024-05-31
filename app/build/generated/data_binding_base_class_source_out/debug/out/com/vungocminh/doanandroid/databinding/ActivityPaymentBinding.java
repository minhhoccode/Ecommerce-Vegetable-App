// Generated by view binder compiler. Do not edit!
package com.vungocminh.doanandroid.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vungocminh.doanandroid.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPaymentBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final BottomNavigationView bottomNavigation;

  @NonNull
  public final AppCompatButton buttonConfirm;

  @NonNull
  public final LinearLayout summary;

  @NonNull
  public final TextView textCheckout;

  @NonNull
  public final EditText textYourAddress;

  @NonNull
  public final TextView textyouraddress;

  private ActivityPaymentBinding(@NonNull LinearLayout rootView,
      @NonNull BottomNavigationView bottomNavigation, @NonNull AppCompatButton buttonConfirm,
      @NonNull LinearLayout summary, @NonNull TextView textCheckout,
      @NonNull EditText textYourAddress, @NonNull TextView textyouraddress) {
    this.rootView = rootView;
    this.bottomNavigation = bottomNavigation;
    this.buttonConfirm = buttonConfirm;
    this.summary = summary;
    this.textCheckout = textCheckout;
    this.textYourAddress = textYourAddress;
    this.textyouraddress = textyouraddress;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPaymentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPaymentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_payment, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPaymentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottom_navigation;
      BottomNavigationView bottomNavigation = ViewBindings.findChildViewById(rootView, id);
      if (bottomNavigation == null) {
        break missingId;
      }

      id = R.id.button_confirm;
      AppCompatButton buttonConfirm = ViewBindings.findChildViewById(rootView, id);
      if (buttonConfirm == null) {
        break missingId;
      }

      id = R.id.summary;
      LinearLayout summary = ViewBindings.findChildViewById(rootView, id);
      if (summary == null) {
        break missingId;
      }

      id = R.id.text_checkout;
      TextView textCheckout = ViewBindings.findChildViewById(rootView, id);
      if (textCheckout == null) {
        break missingId;
      }

      id = R.id.text_your_address;
      EditText textYourAddress = ViewBindings.findChildViewById(rootView, id);
      if (textYourAddress == null) {
        break missingId;
      }

      id = R.id.textyouraddress;
      TextView textyouraddress = ViewBindings.findChildViewById(rootView, id);
      if (textyouraddress == null) {
        break missingId;
      }

      return new ActivityPaymentBinding((LinearLayout) rootView, bottomNavigation, buttonConfirm,
          summary, textCheckout, textYourAddress, textyouraddress);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}