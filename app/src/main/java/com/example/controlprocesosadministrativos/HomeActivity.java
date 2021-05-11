package com.example.controlprocesosadministrativos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    User user;
    TextView welcome;
    private DataBaseHelper DB;
    private RecyclerView recyclerView;
    private RecyclerViewAdaptador adapter;
    private List<com.example.controlprocesosadministrativos.Utility.Menu> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String login=  Help.readFile(this, "login.txt");
        DB = new DataBaseHelper(this);
        user =  DB.userByid(login);

        welcome= (TextView) findViewById(R.id.bienvenido_text);
        welcome.setText(user.nombre);

       recyclerView = (RecyclerView)findViewById(R.id.recycleMenu);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
       adapter= new RecyclerViewAdaptador(getMenus());
       recyclerView.setAdapter(adapter);
       adapter.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Toast.makeText(getApplicationContext(),getMenus().get(recyclerView.getChildAdapterPosition(v)).getTitle(), Toast.LENGTH_LONG ).show();
           }
       });

    }

    public List<com.example.controlprocesosadministrativos.Utility.Menu> getMenus(){
        List<com.example.controlprocesosadministrativos.Utility.Menu> item = new ArrayList<>();
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("Home","descripcion", R.drawable.subject));
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("Home2","descripcion2", R.drawable.subject));
        return item;
    }

    public boolean onCreateOptionsMenu(Menu menu){
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