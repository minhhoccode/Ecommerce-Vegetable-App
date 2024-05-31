package com.vungocminh.doanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vungocminh.doanandroid.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends BaseActivity {

    ActivityRegisterBinding binding;
    String address, fullName, phoneNumber, password;
    FirebaseDatabase db;
    DatabaseReference reference;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#ffffff"));

        }
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sessionManager = new SessionManager(this);

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address = binding.address.getText().toString();
                fullName = binding.fullName.getText().toString();
                phoneNumber = binding.phoneNumber.getText().toString();
                password = binding.password.getText().toString();
                if (!address.isEmpty() && !fullName.isEmpty() && !phoneNumber.isEmpty() && !password.isEmpty()){

                    Users users = new Users(address, fullName, phoneNumber, password);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Users");
                    reference.child(phoneNumber).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.address.setText("");
                            binding.fullName.setText("");
                            binding.phoneNumber.setText("");
                            binding.password.setText("");
                            Toast.makeText(RegisterActivity.this,"Successfuly Updated",Toast.LENGTH_SHORT).show();
                            sessionManager.createLoginSession(phoneNumber, password, fullName, address);
                            sessionManager.setLoginStatus(true);
                            Intent intent = new Intent(RegisterActivity.this, ProductListActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }
        });
        binding.loginLink.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
});
    }
}