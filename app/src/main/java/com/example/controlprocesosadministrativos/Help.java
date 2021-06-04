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
import com.example.controlprocesosadministrativos.Models.Local;
import com.example.controlprocesosadministrativos.Models.Student;
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
        List<Menu> item = new ArrayList<>();
        item.add(new Menu("CARRERAS","administra carreras (Crear, modificar, eliminar)", R.drawable.carrera,"CareerActivities.CareerMenuActivity"));
        item.add(new Menu("ASIGNATURAS","administra asignaturas (Crear, modificar, eliminar)", R.drawable.subject,"CourseActivities.CourseMenuActivity"));
        item.add(new Menu("DIFERIDOS","administrar solicitudes diferidas", R.drawable.test,"DeferredTestActivities.DeferredMenuActivity"));
        item.add(new Menu("PERFIL","administra tu perfil", R.drawable.user,"CareerActivities.CareerMenuActivity"));
        item.add(new Menu("CICLOS","administra los ciclos", R.drawable.date,"CycleActivities.CycleListActivity"));
        item.add(new Menu("DOCENTES","administra los docentes", R.drawable.teacher2,"TeacherActivities.TeacherListActivity"));
        item.add(new Menu("LLENAR DB","Llena con datos inicial la DB", R.drawable.db,"db"));
        return item;
    }

    public static List<Menu> getMenusCareer(){
        List<Menu> item = new ArrayList<>();
        item.add(new Menu("AGREGAR","Agregar una carrera", R.drawable.agregar,"CareerActivities.CareerAddActivity"));
        item.add(new Menu("BUSCAR","Buscar una carrera existente", R.drawable.search,"CareerActivities.CareerSearchActivity"));
       // item.add(new com.example.controlprocesosadministrativos.Utility.Menu("ELIMINAR","Eliminar una carrera existente", R.drawable.eliminar,"CareerActivities.CareerMenuActivity"));
        item.add(new Menu("CARRERAS","Administrar carreras", R.drawable.lista,"CareerActivities.CareerListActivity"));
        return item;
    }



    public static List<Menu> getMenusCourse(){
        List<Menu> item = new ArrayList<>();
        item.add(new Menu("AGREGAR","Agregar una asignatura", R.drawable.agregar,"CourseActivities.CourseAddActivity"));
        item.add(new Menu("BUSCAR","Buscar una asignatura existente", R.drawable.search,"CourseActivities.CourseSearchActivity"));
        item.add(new Menu("ASIGNATURAS","Administrar asignaturas", R.drawable.lista,"CourseActivities.CourseListActivity"));
        return item;
    }


    public static List<Menu> getMenusDeferred(){
        List<Menu> item = new ArrayList<>();
        item.add(new Menu("VER","Ver solicitudes de diferidos", R.drawable.test,"DeferredTestActivities.DeferredListActivity"));
        item.add(new Menu("NUEVA SOLICITUD","Crear solicitud de diferido", R.drawable.agregar,"DeferredTestActivities.DeferredTestAddActivity"));
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
        String[] courseNames={"Programación I","Programación II","Programación III",
                "Estructuras de Datos","Métodos Probabilísticos","Análisis Numérico","Herramientas de Productividad","Arquitectura de Computadoras"
        ,"Sistemas Contables","Diseño de Sistemas I","Microprogramación"};
        String[] code={"PRN115","PRN215","PRN315", "ESD115","MEP115","ANS115","HDP115","ARC115", "SIC115", "DSI115", "MIP115"};
        for (int i = 0; i <code.length ; i++) {
            Course course = new Course();
            course.setCourse( courseNames[i] );
            course.setCodeCourse( code[i]);
            course.setCarrerId( 1 );
            courseList.add(course);
        }
        return courseList;
    }

    public static List<Student> studentsDB(){
        List<Student> studentsList = new ArrayList<>();
        String[] carnets={"NH16001", "MP14001","OP19356","HV10001", "SP01003"};
        String[] name={ "Francisco Jose Navas Hernandez",
                        "Mario Alfonzo Martines Prado",
                        "Olga Daniela Ortega Palacios" ,
                        "Zulma Alma Hernandez Valle",
                        "Samantha Sarai Silva Perez"};

        for (int i = 0; i <carnets.length ; i++) {
            Student student = new Student();
            student.setCarnet( carnets[i] );
            student.setName( name[i]);
            student.setCareerId( 1 );
            studentsList.add(student);
        }
        return studentsList;
    }


    public static List<Local> localDB(){
        List<Local> localLists = new ArrayList<>();
        String[] locals={"Local A", "Local B", "Local C", "Local D", "Local E","Local F"};

        for (int i = 0; i <locals.length ; i++) {
            Local local = new Local();
            local.setLocal( locals[i] );
            localLists.add(local);
        }
        return localLists;
    }



}
