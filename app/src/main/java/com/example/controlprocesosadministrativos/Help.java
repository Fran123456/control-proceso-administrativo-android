package com.example.controlprocesosadministrativos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.controlprocesosadministrativos.Utility.Menu;

import static androidx.core.content.ContextCompat.startActivity;

public class Help  {

    public Help() {
    }

    public static boolean saveFile(String file,String value, Context context){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.getApplicationContext().openFileOutput(file, Context.MODE_PRIVATE);
            fileOutputStream.write(value.getBytes());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static String readFile(Context context, String file){
        FileInputStream fileInputStream = null;
        try{
            fileInputStream =  context.getApplicationContext().openFileInput(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while((line = bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }catch (Exception e){
            return "";
        }
    }

    public static List<Menu> getMenusHome(){
        List<com.example.controlprocesosadministrativos.Utility.Menu> item = new ArrayList<>();
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("CARRERAS","administra carreras (Crear, modificar, eliminar)", R.drawable.carrera,"CareerActivities.CareerMenuActivity"));
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("ASIGNATURA","administra asignaturas (Crear, modificar, eliminar)", R.drawable.subject,"CareerActivities.CareerMenuActivity"));
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("PERFIL","administra tu perfil", R.drawable.user,"CareerActivities.CareerMenuActivity"));
        return item;
    }

    public static List<Menu> getMenusCareer(){
        List<com.example.controlprocesosadministrativos.Utility.Menu> item = new ArrayList<>();
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("AGREGAR","Agregar una carrera", R.drawable.agregar,"CareerActivities.CareerMenuActivity"));
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("MODIFICAR","Modificar una carrera existente", R.drawable.editar,"CareerActivities.CareerMenuActivity"));
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("ELIMINAR","Eliminar una carrera existente", R.drawable.eliminar,"CareerActivities.CareerMenuActivity"));
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("VER TODAS LAS CARRERAS","Listar todas las carreras existentes", R.drawable.lista,"CareerActivities.CareerMenuActivity"));
        return item;
    }



}
