package com.example.electronicjournal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.TokenWatcher;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    //FireBase
    private FirebaseAuth mAuth;
    static FirebaseUser user;
    //кнопки
    EditText Login, Password;
    Button loginbtn;
    //навигация

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Login = findViewById(R.id.login);
        Password = findViewById(R.id.pass);
        loginbtn = findViewById(R.id.BtSql);

        loginbtn.setOnClickListener(view -> {
            loginUser();
        });


    }

    private void loginUser() {
        String login = Login.getText().toString();
        String password = Password.getText().toString();

        if (TextUtils.isEmpty(login)){
            Login.setError("Логин не должен быть пустым");
            Login.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            Password.setError("Пароль не должен быть пустым");
            Password.requestFocus();
        }
        else {
            mAuth.signInWithEmailAndPassword(login,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this,"Вход выполнен",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Ошибка входа" + task.getException(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        user = mAuth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        }
    }
}