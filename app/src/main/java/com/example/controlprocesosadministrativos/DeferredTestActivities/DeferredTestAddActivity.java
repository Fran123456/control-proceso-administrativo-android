package com.example.controlprocesosadministrativos.DeferredTestActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controlprocesosadministrativos.DataBaseHelper;
import com.example.controlprocesosadministrativos.Help;
import com.example.controlprocesosadministrativos.MainActivity;
import com.example.controlprocesosadministrativos.Models.Career;
import com.example.controlprocesosadministrativos.Models.Course;
import com.example.controlprocesosadministrativos.Models.Local;
import com.example.controlprocesosadministrativos.Models.Student;
import com.example.controlprocesosadministrativos.Models.TestDiferred;
import com.example.controlprocesosadministrativos.R;

import java.util.ArrayList;
import java.util.List;

public class DeferredTestAddActivity extends AppCompatActivity {
    Spinner comboLocal;
    private List<Local> localLists;
    private List<String> localsString;
    private DataBaseHelper DB;
    private Local local;
    private Course course;
    private TestDiferred test;
    private Student student;
    private EditText studentSearch;
    private EditText editTime;
    private EditText editDate;
    private EditText courseSearch;
    private Button savebtn;

    private TextView studentSelected;
    private TextView courseSelected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deferred_test_add);
        setTitle("Nueva solicitud de diferido");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        comboLocal= (Spinner)findViewById(R.id.spinnerLocal_deferred);
        DB = new DataBaseHelper(this);
        local= new Local();
        student = new Student();
        course = new Course();
        test = new TestDiferred();
        localLists = new ArrayList<Local>();
        localsString= new ArrayList<String>();
        localLists = DB.getLocals();

        editTime = (EditText)findViewById(R.id.timeAddDeferred_txt);
        editDate= (EditText)findViewById(R.id.dateAddDeferred_txt);
        studentSearch = (EditText)findViewById(R.id.studentSearchAddDeferred_txt);
        studentSelected = (TextView)findViewById(R.id.studentSelected_deferred);
        courseSearch =(EditText)findViewById(R.id.courseSearchAddDeferred_txt);
        courseSelected = (TextView)findViewById(R.id.courseSelected_deferred);
        savebtn = (Button)findViewById(R.id.btnDeferredSave_btn);
        savebtn.setEnabled(false);

        this.LocalListToStringList();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, localsString);
        comboLocal.setAdapter(adapter);

        comboLocal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int i = (int)id;
                int idLocal= localLists.get(i).getId();
                local = DB.getLocal(String.valueOf(idLocal) );
                // course.setCarrerId(0);
                //  Toast.makeText(getApplicationContext() , career.getCareer() , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    public void saveTest(View v){
        test.setDate( editDate.getText().toString()  );
        test.setTime( editTime.getText().toString());
        test.setLocalId( local.getId() );
        test.setCourseId(course.getId());
        test.setStudentId(student.getCarnet());

        editDate.setText("");
        editTime.setText("");
        courseSearch.setText("");
        studentSearch.setText("");
        courseSelected.setText("Asignatura seleccionada: Sin resultado");
        studentSelected.setText("Estudiante seleccionado: Sin resultado");

        String message = DB.addDiferred(test);
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
    }


    public void getStudent(View v){
        student = DB.getStudent(studentSearch.getText().toString());

        if(student.getCarnet() != null){
            studentSelected.setText(student.getCarnet() + " - " + student.getName());
        }else{
            studentSelected.setText("Estudiante seleccionado: Sin resultado en la busqueda");
        }
        this.EnablerBtn();
    }


    public void getCourse(View v){
        course = DB.getCourseByCode(courseSearch.getText().toString());

        if(course.getCodeCourse() != null){
            courseSelected.setText(course.getCodeCourse() + " - " + course.getCourse());
        }else{
            courseSelected.setText("Asignatura seleccionada: Sin resultado en la busqueda");
        }
        this.EnablerBtn();
    }

    private void EnablerBtn(){
        if(course.getCourse() != null && student.getName() != null){
            savebtn.setEnabled(true);
        }
    }



    public void LocalListToStringList(){
        for (int i =0; i<localLists.size(); i++){
            localsString.add(  localLists.get(i).getLocal() );
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