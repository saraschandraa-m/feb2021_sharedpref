package com.nextstacks.sharedprefernce.toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.nextstacks.sharedprefernce.R;

public class ToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        Toolbar mToolbar = findViewById(R.id.tl_toolbar);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Nextstacks");
        }
    }

    //inflater
    @Override
    public boolean onCreateOptionsMenu(Menu men1) {
        getMenuInflater().inflate(R.menu.menu_toolbar, men1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }


    // access_specifier return_type function/method_name (parameters){}
}