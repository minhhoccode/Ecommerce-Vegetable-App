// Generated by view binder compiler. Do not edit!
package com.vungocminh.doanandroid.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public final class ActivityNoConnectionBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView description;

  @NonNull
  public final TextView mainMessage;

  @NonNull
  public final AppCompatButton reloadButton;

  @NonNull
  public final ImageView wifiImage;

  private ActivityNoConnectionBinding(@NonNull RelativeLayout rootView,
      @NonNull TextView description, @NonNull TextView mainMessage,
      @NonNull AppCompatButton reloadButton, @NonNull ImageView wifiImage) {
    this.rootView = rootView;
    this.description = description;
    this.mainMessage = mainMessage;
    this.reloadButton = reloadButton;
    this.wifiImage = wifiImage;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityNoConnectionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityNoConnectionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_no_connection, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityNoConnectionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.description;
      TextView description = ViewBindings.findChildViewById(rootView, id);
      if (description == null) {
        break missingId;
      }

      id = R.id.main_message;
      TextView mainMessage = ViewBindings.findChildViewById(rootView, id);
      if (mainMessage == null) {
        break missingId;
      }

      id = R.id.reload_button;
      AppCompatButton reloadButton = ViewBindings.findChildViewById(rootView, id);
      if (reloadButton == null) {
        break missingId;
      }

      id = R.id.wifi_image;
      ImageView wifiImage = ViewBindings.findChildViewById(rootView, id);
      if (wifiImage == null) {
        break missingId;
      }

      return new ActivityNoConnectionBinding((RelativeLayout) rootView, description, mainMessage,
          reloadButton, wifiImage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
