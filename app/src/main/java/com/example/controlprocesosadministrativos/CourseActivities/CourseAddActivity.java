package com.example.controlprocesosadministrativos.CourseActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.controlprocesosadministrativos.DataBaseHelper;
import com.example.controlprocesosadministrativos.Models.Career;
import com.example.controlprocesosadministrativos.R;

import java.util.ArrayList;
import java.util.List;

public class CourseAddActivity extends AppCompatActivity {

    Spinner comboCareers;
    private List<Career> careersList;
    private List<String> careersString;
    private DataBaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add);

        comboCareers = (Spinner)findViewById(R.id.spinnerCareers_combo);
        DB = new DataBaseHelper(this);
        careersList = new ArrayList<Career>();
        careersString = new ArrayList<String>();
        careersList= DB.getCareers();

        this.CareersListToStringList();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, careersString);
        comboCareers.setAdapter(adapter);
    }

    public void CareersListToStringList(){
        for (int i =0; i<careersList.size(); i++){
            careersString.add(careersList.get(i).getId() + " - " + careersList.get(i).getCareer() );
        }
    }
}