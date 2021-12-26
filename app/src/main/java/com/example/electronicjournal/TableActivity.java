package com.example.electronicjournal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_table);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.Profile:
                                startActivity(new Intent(TableActivity.this,ProfileActivity.class));
                                finish();
                                break;
                            case R.id.Settings:
                                startActivity(new Intent(TableActivity.this,SettingsActivity.class));
                                finish();
                                break;
                        }
                        return false;
                    }
                }
        );
    }
}