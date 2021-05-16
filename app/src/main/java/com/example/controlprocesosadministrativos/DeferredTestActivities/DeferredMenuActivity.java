package com.example.controlprocesosadministrativos.DeferredTestActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.controlprocesosadministrativos.DataBaseHelper;
import com.example.controlprocesosadministrativos.Help;
import com.example.controlprocesosadministrativos.MainActivity;
import com.example.controlprocesosadministrativos.R;
import com.example.controlprocesosadministrativos.RecyclerViewAdaptador;
import com.example.controlprocesosadministrativos.Utility.Menu;

import java.util.List;

public class DeferredMenuActivity extends AppCompatActivity {

    private DataBaseHelper DB;
    private RecyclerView recyclerView;
    private RecyclerViewAdaptador adapter;
    private List<Menu> menuList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deferred_menu);

        setTitle("Evaluaciones diferidas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        menuList = Help.getMenusDeferred(); //obtenemos el menu
        recyclerView = (RecyclerView)findViewById(R.id.recycleMenuDeferred);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new RecyclerViewAdaptador(menuList);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //vamos a pasar a otra activity
                try{
                    Class<?>
                            clase=Class.forName("com.example.controlprocesosadministrativos."+menuList.get(recyclerView.getChildAdapterPosition(v)).getUrl());
                    Intent inte = new Intent(getApplicationContext(), clase);
                    inte.putExtra("titulo",  "Hola" );
                    startActivity(inte);
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
        });
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