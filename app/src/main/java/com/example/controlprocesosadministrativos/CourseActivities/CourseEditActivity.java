package com.example.controlprocesosadministrativos.CourseActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controlprocesosadministrativos.DataBaseHelper;
import com.example.controlprocesosadministrativos.Help;
import com.example.controlprocesosadministrativos.MainActivity;
import com.example.controlprocesosadministrativos.Models.Career;
import com.example.controlprocesosadministrativos.Models.Course;
import com.example.controlprocesosadministrativos.R;

public class CourseEditActivity extends AppCompatActivity {
    private DataBaseHelper DB;
    private Career career;
    private Course course;
    private EditText editCourse;
    private EditText editCodeCourse;
    private TextView viewCareerCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_edit);

        DB = new DataBaseHelper(this);
        career = new Career();
        course = new Course();
        String id = getIntent().getStringExtra("id");
        course = DB.getCourse(id);
        career = DB.getCareer(String.valueOf(course.getCarrerId()));
        setTitle("Editar: " + course.getCourse());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editCodeCourse = (EditText)findViewById(R.id.editCourseCode_txt);
        editCourse = (EditText)findViewById(R.id.editCourse_txt);
        viewCareerCourse = (TextView)findViewById(R.id.editCourseCareer_text);

        editCodeCourse.setText(course.getCodeCourse());
        editCourse.setText(course.getCourse());
        viewCareerCourse.setText(career.getCareer());
    }

    public void editCourse(View v){
        course.setCourse( editCourse.getText().toString() );
        course.setCodeCourse(editCodeCourse.getText().toString());
        String message = DB.editCourse(course);
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