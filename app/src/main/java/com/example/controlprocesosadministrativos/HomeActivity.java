package com.example.controlprocesosadministrativos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    User user;
    TextView welcome;
    private DataBaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String login=  Help.readFile(this, "login.txt");
        DB = new DataBaseHelper(this);
        user =  DB.userByid(login);

       welcome= (TextView) findViewById(R.id.bienvenido_text);
       welcome.setText(user.nombre);

    }
}