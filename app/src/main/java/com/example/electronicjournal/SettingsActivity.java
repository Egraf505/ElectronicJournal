package com.example.electronicjournal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {

    private static FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mAuth = FirebaseAuth.getInstance();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_setting);

        bottomNavigationView.setSelectedItemId(R.id.Settings);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.Profile:
                                startActivity(new Intent(SettingsActivity.this,ProfileActivity.class));
                                finish();
                                break;
                            case R.id.Table:
                                startActivity(new Intent(SettingsActivity.this,TableActivity.class));
                                finish();
                                break;
                        }
                        return false;
                    }
                }
        );

        TextView license = findViewById(R.id.textLicense);
        TextView version = findViewById(R.id.textVersion);
        TextView egg = findViewById(R.id.textEgg);
        TextView exit = findViewById(R.id.Exit);

        license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingsActivity.this,"Вы соглашаетесь на продажу вашей души", Toast.LENGTH_LONG);
            }
        });

        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingsActivity.this,"1.0.0.3", Toast.LENGTH_LONG);
            }
        });

        egg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(SettingsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}