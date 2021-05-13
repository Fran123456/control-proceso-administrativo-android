package com.example.controlprocesosadministrativos.CareerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.controlprocesosadministrativos.DataBaseHelper;
import com.example.controlprocesosadministrativos.Help;
import com.example.controlprocesosadministrativos.MainActivity;
import com.example.controlprocesosadministrativos.Models.Career;
import com.example.controlprocesosadministrativos.R;

public class CareerSearchActivity extends AppCompatActivity {

    private DataBaseHelper DB;
    private Career career;
    private EditText searchCode;
    private TextView codetxt;
    private TextView careertxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_search);
        setTitle("Buscar carrera por codigo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DB = new DataBaseHelper(this);
        career = new Career();
        codetxt = (TextView)findViewById(R.id.searchCode_textView);
        careertxt = (TextView)findViewById(R.id.searchName_textView);

        searchCode = (EditText)findViewById(R.id.searchCodeCareer_txt);

    }

    public void getCareer(View v){
        career = DB.getCareerByCode(searchCode.getText().toString());
        if(career.getCodeCareer() != null){
            careertxt.setText("Codigo de la carrera: "+career.getCareer());
            codetxt.setText("Nombre de la carrera: "+ career.getCodeCareer());
        }else{
            careertxt.setText("");
            codetxt.setText("No se ha encontrado resultado");
        }
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