package com.example.controlprocesosadministrativos.TeacherActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.controlprocesosadministrativos.Help;
import com.example.controlprocesosadministrativos.MainActivity;
import com.example.controlprocesosadministrativos.Models.Teacher;
import com.example.controlprocesosadministrativos.Models.TeacherApi;
import com.example.controlprocesosadministrativos.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeacherListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewTeacher adapter;
    public List<Teacher> menuList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("DOCENTES ");
        menuList = new ArrayList<>();
        getTeachers();
    }


    private void getTeachers(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://nh16001.000webhostapp.com/servicios-web-pdm/").addConverterFactory(GsonConverterFactory.create()).build();
        TeacherApi teacherApi = retrofit.create(TeacherApi.class);
        Call<List<Teacher>> call = teacherApi.contenido();
        List<Teacher> list = new ArrayList<>();
        call.enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                try{
                    if(response.isSuccessful()){
                        list.addAll(response.body());
                        // response.body().get(0).getFecha_fin();
                        // Toast.makeText(getApplicationContext(),"x"+ response.body().get(0).getFecha_fin().toString() , Toast.LENGTH_LONG).show();
                        menuList = response.body();
                        recyclerView = (RecyclerView)findViewById(R.id.recyclerTeacher);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter= new RecyclerViewTeacher(list);
                        recyclerView.setAdapter(adapter);

                        adapter.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                //  Toast.makeText( getApplicationContext() ,"si" , Toast.LENGTH_LONG).show();
                                //vamos a pasar a otra activity

                                try{
                                    Class<?>
                                            clase=Class.forName("com.fiares.UnidadesActivities.UnidadActivity");
                                    Intent inte = new Intent(getApplicationContext(), clase);
                                    inte.putExtra("id", String.valueOf( menuList.get(recyclerView.getChildAdapterPosition(v)).getId() ) );
                                    //inte.putExtra("id",  "Hola" );
                                    startActivity(inte);
                                }catch(ClassNotFoundException e){
                                    e.printStackTrace();
                                }

                            }
                        });
                        //  Toast.makeText(getApplicationContext(),list.size() , Toast.LENGTH_LONG).show();
                    }
                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
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