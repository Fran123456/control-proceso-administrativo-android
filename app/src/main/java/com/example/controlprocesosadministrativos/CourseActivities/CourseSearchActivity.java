package com.example.controlprocesosadministrativos.CourseActivities;

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
import com.example.controlprocesosadministrativos.Models.Course;
import com.example.controlprocesosadministrativos.R;
import com.google.android.material.textfield.TextInputEditText;

public class CourseSearchActivity extends AppCompatActivity {
    private DataBaseHelper DB;
    private Career career;
    private Course course;
    private EditText searchCode;
    private TextView codetxt;
    private TextView coursetxt;
    private TextView courseCareertxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_search);

        setTitle("Buscar asignatura por codigo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DB = new DataBaseHelper(this);
        career = new Career();
        course = new Course();
        codetxt = (TextView)findViewById(R.id.searchCodeCourse_text);
        coursetxt = (TextView)findViewById(R.id.searchCourse_text);
        courseCareertxt = (TextView)findViewById(R.id.searchCourseCareer_text);
        searchCode = (EditText)findViewById(R.id.searchCourse_txt);
    }

    public void getCourse(View v){
        course = DB.getCourseByCode(searchCode.getText().toString());
        career = DB.getCareer(String.valueOf(course.getCarrerId()));
        if(career.getCodeCareer() != null){
            codetxt.setText("Codigo de la asignatura: "+ course.getCodeCourse()  );
            coursetxt.setText("Asignatura: "+ course.getCourse()  );
            courseCareertxt.setText( "Carrera: "+ career.getCareer());
        }else{
            coursetxt.setText("");
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