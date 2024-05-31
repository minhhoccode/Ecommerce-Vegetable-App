package com.vungocminh.doanandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity  extends BaseActivity {

    private Button signInWithEmail;
    private TextView createAccount;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#ffffff"));

        }
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        setContentView(R.layout.activity_main);

        signInWithEmail = findViewById(R.id.sign_in_with_email);
        createAccount = findViewById(R.id.create_account);

        sessionManager = new SessionManager(this);

        if (sessionManager.getLoginStatus()) {
            Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
            startActivity(intent);
            finish();
        }

        signInWithEmail.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
});

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}