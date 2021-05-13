package com.example.controlprocesosadministrativos.CareerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlprocesosadministrativos.DataBaseHelper;
import com.example.controlprocesosadministrativos.Help;
import com.example.controlprocesosadministrativos.MainActivity;
import com.example.controlprocesosadministrativos.Models.Career;
import com.example.controlprocesosadministrativos.R;

public class CareerEditActivity extends AppCompatActivity {
    private DataBaseHelper DB;
    private Career career;
    private EditText editCareer;
    private EditText editCodeCareer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_edit);
        DB = new DataBaseHelper(this);
        career = new Career();
        String id = getIntent().getStringExtra("id");
        career = DB.getCareer(id);
        setTitle("Editar "+career.getCareer());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editCareer = (EditText)findViewById(R.id.editCareer_txt);
        editCodeCareer=(EditText)findViewById(R.id.editCodeCareer_txt);

        editCodeCareer.setText( career.getCodeCareer() );
        editCareer.setText( career.getCareer() );

    }

    public void editCareer(View v){
        career.setCareer( editCareer.getText().toString() );
        career.setCodeCareer(editCodeCareer.getText().toString());
        String message = DB.editCareer(career);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.menu_salir_menu){
            Help.saveFile("login.txt", "0", this);
            startActivity(new Intent(this , MainActivity.class));
            finish();
            // Toast.makeText(this, "xxx", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }




}