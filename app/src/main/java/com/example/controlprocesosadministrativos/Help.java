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

import com.example.controlprocesosadministrativos.Models.Career;
import com.example.controlprocesosadministrativos.Models.Course;
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
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("ASIGNATURAS","administra asignaturas (Crear, modificar, eliminar)", R.drawable.subject,"CourseActivities.CourseMenuActivity"));
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("PERFIL","administra tu perfil", R.drawable.user,"CareerActivities.CareerMenuActivity"));
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("LLENAR DB","Llena con datos inicial la DB", R.drawable.db,"db"));
        return item;
    }

    public static List<Menu> getMenusCareer(){
        List<com.example.controlprocesosadministrativos.Utility.Menu> item = new ArrayList<>();
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("AGREGAR","Agregar una carrera", R.drawable.agregar,"CareerActivities.CareerAddActivity"));
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("BUSCAR","Buscar una carrera existente", R.drawable.search,"CareerActivities.CareerSearchActivity"));
       // item.add(new com.example.controlprocesosadministrativos.Utility.Menu("ELIMINAR","Eliminar una carrera existente", R.drawable.eliminar,"CareerActivities.CareerMenuActivity"));
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("CARRERAS","Administrar carreras", R.drawable.lista,"CareerActivities.CareerListActivity"));
        return item;
    }



    public static List<Menu> getMenusCourse(){
        List<com.example.controlprocesosadministrativos.Utility.Menu> item = new ArrayList<>();
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("AGREGAR","Agregar una asignatura", R.drawable.agregar,"CourseActivities.CourseAddActivity"));
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("BUSCAR","Buscar una asignatura existente", R.drawable.search,"CourseActivities.CourseSearchActivity"));
        item.add(new com.example.controlprocesosadministrativos.Utility.Menu("ASIGNATURAS","Administrar asignaturas", R.drawable.lista,"CourseActivities.CourseListActivity"));
        return item;
    }



    public static List<Career> carreersDB(){
        List<Career> careerList= new ArrayList<>();
        //CARRERAS
        String[] careerNames={"Ingenieria en sistemas informaticos","Ingenieria en Alimentos","Ingenieria industrial",
                "Ingenieria Electrica","Ingenieria Mecanica"};
        String[] code={"IEI","IEA","IID", "IEL","IMC"};
        for (int i = 0; i <code.length ; i++) {
            Career career = new Career();
            career.setCodeCareer( code[i] );
            career.setCareer( careerNames[i]);
            careerList.add(career);
        }
        return careerList;
    }

    public static List<Course> courseDB(){
        List<Course> courseList = new ArrayList<>();
        //courses
        String[] courseNames={"Programación para dispositivos moviles","Herramientas de productividad","Sistema de información gerencial",
                "Recursos Humanos"};
        String[] code={"PDM115","HDP115","SIG115", "RHU115"};
        for (int i = 0; i <code.length ; i++) {
            Course course = new Course();
            course.setCourse( courseNames[i] );
            course.setCodeCourse( code[i]);
            course.setCarrerId( 1 );
            courseList.add(course);
        }
        return courseList;
    }

}
