package com.example.electronicjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.os.TokenWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    //Переменные для Sql
    private static String ip = "192.168.206.17";
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "ElectronikJournalDB";
    private static String username = "admin";
    private static String password = "admin";
    private static String Url = "jdbc:jtds:sqlserver://"+port+"/"+database+";instance=SQLEXPRESS";
    //подключение
    private Connection connection = null;
    //потоки
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    //кнопки


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(Url, username, password);
            Log.i("Connect", "Успешно");
        } catch (SQLException | ClassNotFoundException throwables) {
            Log.i("Connect", "Неудачно");
            throwables.printStackTrace();
        }
    }
    private void SqlConnectBt(View v){
        TextView login = findViewById(R.id.login);
        TextView pass = findViewById(R.id.pass);

        if (login.getText() == null || pass.getText() == null){
            Toast.makeText(this,"Поля не должны быть пустыми",Toast.LENGTH_LONG);
        }
        else {
            String loginDB = login.getText().toString();
            String passDB = pass.getText().toString();
        }
    }
}