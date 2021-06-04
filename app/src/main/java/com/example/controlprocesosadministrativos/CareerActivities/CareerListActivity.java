package com.example.controlprocesosadministrativos.CareerActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.controlprocesosadministrativos.DataBaseHelper;
import com.example.controlprocesosadministrativos.Help;
import com.example.controlprocesosadministrativos.MainActivity;
import com.example.controlprocesosadministrativos.Models.Career;
import com.example.controlprocesosadministrativos.R;

import java.util.ArrayList;
import java.util.List;

public class CareerListActivity extends AppCompatActivity {

    private DataBaseHelper DB;
    private List<Career> careersList;
    private RecyclerView recyclerView;
    private RecyclerViewCareer adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_list);

        setTitle("Listar carreras");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DB = new DataBaseHelper(this);
        careersList = new ArrayList<Career>();
        careersList= DB.getCareers();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerTeacher);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         adapter= new RecyclerViewCareer(careersList);
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