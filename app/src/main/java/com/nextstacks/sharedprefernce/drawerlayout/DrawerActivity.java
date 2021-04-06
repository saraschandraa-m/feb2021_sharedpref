package com.nextstacks.sharedprefernce.drawerlayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.nextstacks.sharedprefernce.R;

public class DrawerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        Toolbar mToolbar = findViewById(R.id.tl_drawer);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Drawer Layout");
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navView = findViewById(R.id.nav_drawer);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(DrawerActivity.this, drawerLayout, mToolbar, R.string.open, R.string.close);
        drawerToggle.syncState();


        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if (item.getItemId() == R.id.action_archive) {
//                    Toast.makeText(DrawerActivity.this, "Archive Clicked", Toast.LENGTH_LONG).show();
//                }

                String message = "";
                switch (item.getItemId()) {
                    case R.id.action_archive:
                        message = "Archive Clicked";
                        break;

                    case R.id.action_delete:
                        message = "Delete Clicked";
                        break;

                    case R.id.action_edit:
                        message = "Edit Clicked";
                        break;

                    default:
                        message = "Move Clicked";
                        break;
                }

                Toast.makeText(DrawerActivity.this, message, Toast.LENGTH_LONG).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}
