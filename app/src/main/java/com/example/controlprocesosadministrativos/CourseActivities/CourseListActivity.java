package com.example.controlprocesosadministrativos.CourseActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.controlprocesosadministrativos.CareerActivities.RecyclerViewCareer;
import com.example.controlprocesosadministrativos.DataBaseHelper;
import com.example.controlprocesosadministrativos.Help;
import com.example.controlprocesosadministrativos.MainActivity;
import com.example.controlprocesosadministrativos.Models.Course;
import com.example.controlprocesosadministrativos.R;

import java.util.ArrayList;
import java.util.List;

public class CourseListActivity extends AppCompatActivity {

    private DataBaseHelper DB;
    private List<Course> coursesList;
    private RecyclerView recyclerView;
    private RecyclerViewCourse adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        setTitle("Listar asignaturas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DB = new DataBaseHelper(this);
        coursesList= new ArrayList<Course>();
        coursesList= DB.getCourses();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerCourse);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new RecyclerViewCourse(coursesList);
        recyclerView.setAdapter(adapter);
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