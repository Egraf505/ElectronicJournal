package com.example.electronicjournal;

import static com.example.electronicjournal.MainActivity.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.common.collect.Table;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private TextView name ;
    private TextView secondname;
    private TextView group;
    private  TextView nb;

    private DocumentReference myRef;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_profile);

        bottomNavigationView.setSelectedItemId(R.id.Profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.Table:
                                startActivity(new Intent(ProfileActivity.this,TableActivity.class));
                                finish();
                                break;
                            case R.id.Settings:
                                startActivity(new Intent(ProfileActivity.this,SettingsActivity.class));
                                finish();
                                break;
                        }
                        return false;
                    }
                }
        );

        name = findViewById(R.id.name_user);
        secondname = findViewById(R.id.secondname_user);
        group = findViewById(R.id.group_user);
        nb = findViewById(R.id.list_nb);

        myRef = db.collection(user.getUid()).document("User");
        myRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.e("FIRE", "DocumentSnapshot data: " + document.getData());
                        name.setText(document.getString("Name"));
                        secondname.setText(document.getString("Secondname"));
                        group.setText("Группа: "+ document.getString("Group"));
                    } else {
                        Log.e("FIRE", "No such document");
                    }
                }
                else {
                    Log.d("FIRE", "get failed with ", task.getException());
                }
            }
        });

    }

}