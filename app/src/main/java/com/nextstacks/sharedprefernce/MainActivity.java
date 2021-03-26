package com.nextstacks.sharedprefernce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String username;
    private String password;

    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPasword);

        Button mBtnLogin = findViewById(R.id.btnLogin);

        SharedPreferences prefManager = getApplicationContext().getSharedPreferences("NEXTSTACKS", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefManager.edit();


        mBtnLogin.setOnClickListener(v -> {
            username = etUsername.getText().toString();
            password = etPassword.getText().toString();

            editor.putString("USERNAME", username);
            editor.putString("PASSWORD", password);
            editor.putBoolean("ISLOGGEDIN", true);
//            editor.commit();

            editor.apply();
            doLoginProcess();
        });

        boolean isLoggedIn = prefManager.getBoolean("ISLOGGEDIN", false);

        if (isLoggedIn) {
            doLoginProcess();
        }
    }


    private void doLoginProcess() {
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
        finish();
    }
}