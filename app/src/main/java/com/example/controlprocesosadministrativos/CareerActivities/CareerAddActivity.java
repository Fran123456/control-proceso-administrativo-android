package com.example.controlprocesosadministrativos.CareerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlprocesosadministrativos.DataBaseHelper;
import com.example.controlprocesosadministrativos.Help;
import com.example.controlprocesosadministrativos.MainActivity;
import com.example.controlprocesosadministrativos.Models.Career;
import com.example.controlprocesosadministrativos.R;

public class CareerAddActivity extends AppCompatActivity {

    private DataBaseHelper DB;
    public Career careerObj;
    EditText editCareerCode;
    EditText editCareer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_add);

        setTitle("Agregar carreras");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editCareer = (EditText) findViewById(R.id.careerAdd_txt);
        editCareerCode= (EditText) findViewById(R.id.careerCode_txt);
        careerObj = new Career();
        DB = new DataBaseHelper(this);
    }

    public void AddCareerView(View v){
        String career = editCareer.getText().toString();
        String codeCareer = editCareerCode.getText().toString();

          careerObj.setCareer(career);
          careerObj.setCodeCareer(codeCareer);

        String message = DB.addCareer(careerObj);
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
        this.clean( v);
    }

    public void clean(View v){
        editCareerCode.setText("");
        editCareer.setText("");

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