package com.example.controlprocesosadministrativos;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usuario, password;
    private DataBaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Accede");
        usuario = findViewById(R.id.usuario_txt);
        password = findViewById(R.id.password_txt);
        DB = new DataBaseHelper(this);


        try{
            String login=  Help.readFile(this, "login.txt");
            if(!login.equals("0")){
                Intent inte = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(inte);
            }
        }catch (Exception e){
            Help.saveFile("login.txt", "0", this);
        }
    }

    public void login(View v){
                boolean var = DB.checkUser(usuario.getText().toString() , password.getText().toString());
                if (var){
                    Help.saveFile("login.txt", "1", this);
                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this , HomeActivity.class));
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Login Failed !!", Toast.LENGTH_SHORT).show();
                }
    }


    public void registrar(View v){
        Intent inte = new Intent(getApplicationContext(), RegisterActivity.class);
        //inte.putExtra("titulo",   );
        startActivity(inte);
    }
}