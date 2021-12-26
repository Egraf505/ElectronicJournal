package com.example.electronicjournal;

import static com.example.electronicjournal.MainActivity.user;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class TableActivity extends AppCompatActivity {

    private TextView tittle;
    private TextView score;

    private TableLayout table;

    private DocumentReference myRef;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_table);

        bottomNavigationView.setSelectedItemId(R.id.Table);

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

        table = findViewById(R.id.table_layout);

        tittle = findViewById(R.id.title_items);
        score = findViewById(R.id.title_score);

        myRef = db.collection(user.getUid()).document("Items");
        myRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        List<String> keys = GetListkey(document);
                        Log.e("LIST",keys.toString());
                        for (String key : keys){
                            List<String> item = (List<String>) document.get(key);
                            AddTableRow(item,key);
                        }
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void AddTableRow(List<String> score, String name){
        TableRow row = new TableRow(this);

        TextView title = new TextView(this);
        title.setBackgroundResource(R.drawable.border_around);
        title.setText(name);
        title.setTextSize(10);
        row.addView(title);

        TextView score_items = new TextView(this);
        score_items.setBackgroundResource(R.drawable.border_around);
        String buf = "";
        for (String str : score){
            buf += str+" ";
        }
        score_items.setText(buf);
        score_items.setTextSize(15);
        row.addView(score_items);

        table.addView(row);
    }

    private List<String> GetListkey(DocumentSnapshot document){
        List<String> list = new ArrayList<>();

        Map<String, Object> map = document.getData();
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(entry.getKey());
            }
        }
        return list;
    }
}