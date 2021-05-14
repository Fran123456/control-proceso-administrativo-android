package com.example.controlprocesosadministrativos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.controlprocesosadministrativos.Models.Career;
import com.example.controlprocesosadministrativos.Models.Course;
import com.example.controlprocesosadministrativos.Tables.Tables;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CONTROL_PROCESO_ADMINISTRATIVO";
    private Tables tables;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+tables.userTable + "("+tables.userFields[0]+" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " "+tables.userFields[1]+" TEXT ," +
                ""+tables.userFields[2]+" TEXT , "+tables.userFields[3]+" TEXT )");

        db.execSQL("CREATE TABLE IF NOT EXISTS "+tables.careerTable + "("+tables.careerFields[0]+" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " "+tables.careerFields[1]+" TEXT ," +
                ""+tables.careerFields[2]+" TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS "+tables.courseTable+ "("+tables.courseFields[0]+" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " "+tables.courseFields[1]+" TEXT ," +
                ""+tables.courseFields[2]+" TEXT," +
                ""+tables.courseFields[3]+" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + tables.userTable);
        db.execSQL(" DROP TABLE IF EXISTS " + tables.careerTable);
        db.execSQL(" DROP TABLE IF EXISTS " + tables.courseTable);
        onCreate(db);
    }

  //METODOS PARA USUARIO//
    public boolean registerUser(String username , String email , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tables.userFields[1] , username);
        values.put(tables.userFields[2], email);
        values.put(tables.userFields[3] , password);

        long result = db.insert(tables.userTable , null , values);
        if(result == -1) return false;
        else return true;
    }

    public boolean validateUser(String username , String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = { tables.userFields[0] };
        String selection = tables.userFields[1] + "=?" + " and " + tables.userFields[2]+ "=?";
        String [] selectionargs = { username , email};
        Cursor cursor = db.query(tables.userTable, columns , selection ,selectionargs , null , null , null);
        int count = cursor.getCount();
        db.close();
        cursor.close();
        if (count > 0) return true;
        else return false;
    }

    public boolean checkUser(String username , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = { tables.userFields[0] };
        String selection = tables.userFields[1] + "=?" + " and " + tables.userFields[3]+ "=?";
        String [] selectionargs = { username , password};
        Cursor cursor = db.query(tables.userTable, columns , selection ,selectionargs , null , null , null);
       int count = cursor.getCount();
        db.close();
        cursor.close();
        if (count > 0) return true;
        else return false;
    }

    public User userByUsernameAndPassword(String username , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = { tables.userFields[0] };
        String selection = tables.userFields[1] + "=?" + " and " + tables.userFields[3]+ "=?";
        String [] selectionargs = { username , password};
        Cursor cursor = db.query(tables.userTable, columns , selection ,selectionargs , null , null , null);
        User user = new User();
        if(cursor.moveToFirst()){

            user.setId( Integer.parseInt(cursor.getString(0))   );

            db.close();
        }else{
            db.close();
            user.setId(0);
        }
        return user;
    }

    public User userByid(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = { tables.userFields[0], tables.userFields[1], tables.userFields[2] };
        String selection = tables.userFields[0] + "=?";
        String [] selectionargs = { id };
        Cursor cursor = db.query(tables.userTable, columns , selection ,selectionargs , null , null , null);
        User user = new User();
        if(cursor.moveToFirst()){
            user.setId( Integer.parseInt(cursor.getString(0))   );
            user.setNombre(cursor.getString(1));
            user.setUsuario(cursor.getString(2));
            db.close();
        }else{
            db.close();
            user.setId(0);
        }
        return user;
    }
    //METODOS PARA USUARIO//


     //METODOS PARA CARRERA
     public String addCareer(Career career){
         SQLiteDatabase db = this.getWritableDatabase();
         String message="";
         long contador=0;
         ContentValues careerValues = new ContentValues();
         careerValues.put(tables.careerFields[1], career.getCodeCareer() );
         careerValues.put(tables.careerFields[2], career.getCareer()  );
         contador=db.insert(tables.careerTable, null, careerValues);

         if(contador==-1 || contador==0)
         {
             message= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
         }
         else {
             message="Carrera agregada correctamente";
         }
         db.close();
         return message;
     }

     public List<Career> getCareers(){
         SQLiteDatabase db = this.getWritableDatabase();
         List<Career> careers= new ArrayList<Career>();
         Career career;
         Cursor cursor = db.query(tables.careerTable, tables.careerFields, null, null, null , null, null);

         while(cursor.moveToNext()){
             career  = new Career();
             career.setId(cursor.getInt(0));
             career.setCodeCareer(cursor.getString(1));
             career.setCareer(cursor.getString(2));
             careers.add(career);
         }
         db.close();
         return careers;
     }

     public String deleteCareer(int id){
         SQLiteDatabase db = this.getWritableDatabase();
         String regAfectados="Carrera eliminada correctamente";
         int contador=0;
         //  if (verificarIntegridad(alumno,3)) {
         //     contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
         // }
         contador+=db.delete(tables.careerTable, tables.careerFields[0]+"='"+id+"'", null);
         db.close();
         return regAfectados;
     }

    public Career getCareer(String ids){
        SQLiteDatabase db = this.getWritableDatabase();
        Career career = new Career();

        String[] id = {ids};
        Cursor cursor = db.query(tables.careerTable, tables.careerFields, tables.careerFields[0]+" = ?",
                id, null, null, null);

        if(cursor.moveToFirst()){

            career.setId(cursor.getInt(0));
            career.setCodeCareer(cursor.getString(1));
            career.setCareer(cursor.getString(2));
            db.close();
            return career;
        }else{
            db.close();
            return career;
        }
    }

    public Career getCareerByCode(String code){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] codeCareer = {code};
        Cursor cursor = db.query(tables.careerTable, tables.careerFields, tables.careerFields[1]+" = ?",
                codeCareer , null, null, null);
        Career career = new Career();
        if(cursor.moveToFirst()){
            career.setId(cursor.getInt(0));
            career.setCodeCareer(cursor.getString(1));
            career.setCareer(cursor.getString(2));
            db.close();
            return career;
        }else{
            db.close();
            return career;
        }
    }

    public String editCareer (Career career){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] id = { String.valueOf(career.getId()) };
        ContentValues cv = new ContentValues();
        cv.put(tables.careerFields[1], career.getCodeCareer());
        cv.put(tables.careerFields[2], career.getCareer());
        db.update(tables.careerTable, cv, tables.careerFields[0]+" = ?", id);
        return "Registro Actualizado Correctamente";
    }
    //METODOS PARA CARRERA


    //METODOS PARA ASIGNATURA
    public String addCourse(Course course){
        SQLiteDatabase db = this.getWritableDatabase();
        String message="";
        long contador=0;
        ContentValues courseValues = new ContentValues();
        courseValues.put(tables.courseFields[1], course.getCodeCourse() );
        courseValues.put(tables.courseFields[2], course.getCourse()  );
        courseValues.put(tables.courseFields[3], course.getCarrerId() ) ;
        contador=db.insert(tables.courseTable, null, courseValues);

        if(contador==-1 || contador==0)
        {
            message= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            message="Asignatura agregada correctamente";
        }
        db.close();
        return message;
    }


    public List<Course> getCourses(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Course> courses= new ArrayList<Course>();
        Course course;
        Cursor cursor = db.query(tables.courseTable, tables.courseFields, null, null, null , null, null);

        while(cursor.moveToNext()){
            course  = new Course();
            course.setId(cursor.getInt(0));
            course. setCodeCourse(cursor.getString(1));
            course.setCourse(cursor.getString(2));
            course.setCarrerId(cursor.getInt(3));
           courses.add(course);
        }
        db.close();
        return courses;
    }
    //METODOS PARA ASIGNATURA
}