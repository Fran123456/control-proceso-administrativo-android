package com.example.controlprocesosadministrativos.TeacherActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlprocesosadministrativos.CycleActivities.RecyclerViewCycle;
import com.example.controlprocesosadministrativos.Help;
import com.example.controlprocesosadministrativos.MainActivity;
import com.example.controlprocesosadministrativos.Models.Cycle;
import com.example.controlprocesosadministrativos.Models.CycleApi;
import com.example.controlprocesosadministrativos.Models.Success;
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

public class TeacherAddActivity extends AppCompatActivity {
    EditText name;
    EditText apellido;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("AGREGAR DOCENTES ");

        name = findViewById(R.id.textNombreDocente);
        apellido =findViewById(R.id.textApellidoDocente);
       btn= findViewById(R.id.agregarBtnDocentex);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               AddTeacher();
            }
        });

    }

    public void AddTeacher(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://nh16001.000webhostapp.com/servicios-web-pdm/").addConverterFactory(GsonConverterFactory.create()).build();
        TeacherApi teacherApi = retrofit.create(TeacherApi.class);
        Call<Success> call =  teacherApi.agregar(name.getText().toString(), apellido.getText().toString());
       // NoSuchFieldError list = new ArrayList<>();
        call.enqueue(new Callback<Success>() {

            @Override
            public void onResponse(Call<Success> call, Response<Success> response) {
                try{
                    if(response.isSuccessful()){
                        //list.addAll(response.body());
                        Toast.makeText(getApplicationContext(),"Docente agregado correctamente" , Toast.LENGTH_LONG).show();
                        try{
                            Class<?>
                                    clase=Class.forName("com.example.controlprocesosadministrativos.TeacherActivities.TeacherListActivity");
                               Intent inte = new Intent(getApplicationContext(), clase);
                            //inte.putExtra("id", String.valueOf( menuList.get(recyclerView.getChildAdapterPosition(v)).getId() ) );
                            //inte.putExtra("id",  "Hola" );
                            startActivity(inte);
                        }catch(ClassNotFoundException e){
                            e.printStackTrace();
                        }
                        //  Toast.makeText(getApplicationContext(),list.size() , Toast.LENGTH_LONG).show();
                    }
                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Success> call, Throwable t) {
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