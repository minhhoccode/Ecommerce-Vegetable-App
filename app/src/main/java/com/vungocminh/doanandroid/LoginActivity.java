package com.vungocminh.doanandroid;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.vungocminh.doanandroid.databinding.ActivityLoginBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity  extends BaseActivity {
    ActivityLoginBinding binding;
    String phoneNumber, password;
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

        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        TextView registerLink = findViewById(R.id.registerLink);
        SpannableString content = new SpannableString(registerLink.getText().toString());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        registerLink.setText(content);

        sessionManager = new SessionManager(this);

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phoneNumber = binding.phoneNumber.getText().toString();
                password = binding.password.getText().toString();
                if (!phoneNumber.isEmpty() && !password.isEmpty()){

                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Users");
                    reference.child(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Users user = snapshot.getValue(Users.class);
                            if (user != null) {
                                if (user.getPassword().equals(password)) {
                                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                    sessionManager.createLoginSession(phoneNumber, password, user.getFullName(), user.getAddress());
                                    sessionManager.setLoginStatus(true);
                                    Intent intent = new Intent(LoginActivity.this, ProductListActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this,"Incorrect password",Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this,"User not found",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(LoginActivity.this,"Error occurred",Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });
        binding.registerLink.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
});
    }
}