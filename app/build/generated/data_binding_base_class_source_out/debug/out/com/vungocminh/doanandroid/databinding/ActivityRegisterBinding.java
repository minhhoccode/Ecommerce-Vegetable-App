// Generated by view binder compiler. Do not edit!
package com.vungocminh.doanandroid.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.vungocminh.doanandroid.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final EditText address;

  @NonNull
  public final ImageView appleLogin;

  @NonNull
  public final ImageView facebookLogin;

  @NonNull
  public final EditText fullName;

  @NonNull
  public final ImageView googleLogin;

  @NonNull
  public final TextView loginLink;

  @NonNull
  public final EditText password;

  @NonNull
  public final EditText phoneNumber;

  @NonNull
  public final AppCompatButton registerBtn;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final ImageView vegeIcon;

  private ActivityRegisterBinding(@NonNull ScrollView rootView, @NonNull EditText address,
      @NonNull ImageView appleLogin, @NonNull ImageView facebookLogin, @NonNull EditText fullName,
      @NonNull ImageView googleLogin, @NonNull TextView loginLink, @NonNull EditText password,
      @NonNull EditText phoneNumber, @NonNull AppCompatButton registerBtn,
      @NonNull TextView textView2, @NonNull ImageView vegeIcon) {
    this.rootView = rootView;
    this.address = address;
    this.appleLogin = appleLogin;
    this.facebookLogin = facebookLogin;
    this.fullName = fullName;
    this.googleLogin = googleLogin;
    this.loginLink = loginLink;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.registerBtn = registerBtn;
    this.textView2 = textView2;
    this.vegeIcon = vegeIcon;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.address;
      EditText address = ViewBindings.findChildViewById(rootView, id);
      if (address == null) {
        break missingId;
      }

      id = R.id.appleLogin;
      ImageView appleLogin = ViewBindings.findChildViewById(rootView, id);
      if (appleLogin == null) {
        break missingId;
      }

      id = R.id.facebookLogin;
      ImageView facebookLogin = ViewBindings.findChildViewById(rootView, id);
      if (facebookLogin == null) {
        break missingId;
      }

      id = R.id.fullName;
      EditText fullName = ViewBindings.findChildViewById(rootView, id);
      if (fullName == null) {
        break missingId;
      }

      id = R.id.googleLogin;
      ImageView googleLogin = ViewBindings.findChildViewById(rootView, id);
      if (googleLogin == null) {
        break missingId;
      }

      id = R.id.loginLink;
      TextView loginLink = ViewBindings.findChildViewById(rootView, id);
      if (loginLink == null) {
        break missingId;
      }

      id = R.id.password;
      EditText password = ViewBindings.findChildViewById(rootView, id);
      if (password == null) {
        break missingId;
      }

      id = R.id.phoneNumber;
      EditText phoneNumber = ViewBindings.findChildViewById(rootView, id);
      if (phoneNumber == null) {
        break missingId;
      }

      id = R.id.registerBtn;
      AppCompatButton registerBtn = ViewBindings.findChildViewById(rootView, id);
      if (registerBtn == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.vegeIcon;
      ImageView vegeIcon = ViewBindings.findChildViewById(rootView, id);
      if (vegeIcon == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((ScrollView) rootView, address, appleLogin, facebookLogin,
          fullName, googleLogin, loginLink, password, phoneNumber, registerBtn, textView2,
          vegeIcon);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}