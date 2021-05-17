package com.example.controlprocesosadministrativos.DeferredTestActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.controlprocesosadministrativos.DataBaseHelper;
import com.example.controlprocesosadministrativos.Models.Course;
import com.example.controlprocesosadministrativos.Models.TestDiferred;
import com.example.controlprocesosadministrativos.R;

import java.util.ArrayList;
import java.util.List;

public class DeferredListActivity extends AppCompatActivity {
    private DataBaseHelper DB;
    private List<TestDiferred> testList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deferred_list);

        setTitle("solicitudes de diferido");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DB = new DataBaseHelper(this);
         testList= new ArrayList<TestDiferred>();
         testList= DB.getTests();


    }
}