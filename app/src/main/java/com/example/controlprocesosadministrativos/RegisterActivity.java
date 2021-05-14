package com.example.controlprocesosadministrativos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText user, password, email;
    private DataBaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Registrate");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        user = findViewById(R.id.usuarioR_txt);
        password = findViewById(R.id.passwordR_txt);
        email = findViewById(R.id.emailR_txt);
        DB = new DataBaseHelper(this);

    }

    public void register(View v){
       boolean validate =  DB.validateUser( user.getText().toString(), email.getText().toString(),password.getText().toString() );

      if(validate) {
           Toast.makeText(RegisterActivity.this, "El usuario ya existe, intente con otro usuario o correo", Toast.LENGTH_SHORT).show();
       }else{
           boolean var = DB.registerUser(user.getText().toString() , email.getText().toString() , password.getText().toString());
           if(var){

                User u = DB.userByUsernameAndPassword(user.getText().toString(),  password.getText().toString());
                Integer id = u.getId();
                Help.saveFile("login.txt", id.toString() , this);
               // Toast.makeText(RegisterActivity.this, u.getId(), Toast.LENGTH_SHORT).show();
                this.login(v);
           }
           else{
               Toast.makeText(RegisterActivity.this, "El registro ha fallado, intente de nuevo", Toast.LENGTH_SHORT).show();
           }
       }
    }

    public void login(View v){
        boolean var = DB.checkUser(user.getText().toString() , password.getText().toString());
        if (var){
            User u = DB.userByUsernameAndPassword(user.getText().toString(),  password.getText().toString());
            Integer id = u.getId();
            Help.saveFile("login.txt", id.toString() , this);
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this , HomeActivity.class));
            finish();
        }else{
            Toast.makeText(this, "Error al intentar acceder", Toast.LENGTH_SHORT).show();
        }
    }


}