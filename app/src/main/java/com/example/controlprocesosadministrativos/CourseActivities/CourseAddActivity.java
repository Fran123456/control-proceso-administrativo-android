package com.example.controlprocesosadministrativos.CourseActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.controlprocesosadministrativos.DataBaseHelper;
import com.example.controlprocesosadministrativos.Models.Career;
import com.example.controlprocesosadministrativos.Models.Course;
import com.example.controlprocesosadministrativos.R;

import java.util.ArrayList;
import java.util.List;import java.util.Objects;
import java.util.Properties;


public class CourseAddActivity extends AppCompatActivity {

    Spinner comboCareers;
    private List<Career> careersList;
    private List<String> careersString;
    private DataBaseHelper DB;
    private Career career;
    private Course course;
    private EditText editCodeCourse;
    private EditText editCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add);
        setTitle("Agrega una asignatura");
        comboCareers = (Spinner)findViewById(R.id.spinnerCareers_combo);
        DB = new DataBaseHelper(this);
        course = new Course();
        careersList = new ArrayList<Career>();
        careersString = new ArrayList<String>();
        careersList= DB.getCareers();

        editCodeCourse = (EditText)findViewById(R.id.addCodeCourse_txt);
        editCourse = (EditText)findViewById(R.id.addCourse_txt);


        this.CareersListToStringList();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, careersString);
        comboCareers.setAdapter(adapter);

        comboCareers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int i = (int)id;
                    int idCareer = careersList.get(i).getId();
                    career = DB.getCareer(String.valueOf(idCareer) );
                   // course.setCarrerId(0);
                   //  Toast.makeText(getApplicationContext() , career.getCareer() , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void AddCourseView(View v){
        String stringCourse = editCourse.getText().toString();
        String StringCodeCourse= editCodeCourse.getText().toString();

        course.setCourse(stringCourse);
        course.setCodeCourse(StringCodeCourse);
        course.setCarrerId(career.getId());

        String message = DB.addCourse(course);
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
        this.clean( v);
    }

    public void clean(View v){
        editCodeCourse.setText("");
        editCourse.setText("");
    }

    public void CareersListToStringList(){
        for (int i =0; i<careersList.size(); i++){
            careersString.add( "("+careersList.get(i).getCodeCareer() + ") " + careersList.get(i).getCareer() );
        }
    }
}