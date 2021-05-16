package com.example.controlprocesosadministrativos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.controlprocesosadministrativos.Models.Career;
import com.example.controlprocesosadministrativos.Models.Course;
import com.example.controlprocesosadministrativos.Models.Local;
import com.example.controlprocesosadministrativos.Models.Student;
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

        db.execSQL("CREATE TABLE IF NOT EXISTS "+tables.studentTable + "("+tables.studentFields[0]+" VARCHAR(7) NOT NULL PRIMARY KEY ," +
                " "+tables.studentFields[1]+" TEXT ," +
                ""+tables.studentFields[2]+" TEXT)");


        db.execSQL("CREATE TABLE IF NOT EXISTS "+tables.localTable + "("+tables.localFields[0]+" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                ""+tables.localFields[1]+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL(" DROP TABLE IF EXISTS " + tables.userTable);
        db.execSQL(" DROP TABLE IF EXISTS " + tables.careerTable);
        db.execSQL(" DROP TABLE IF EXISTS " + tables.courseTable);
        db.execSQL(" DROP TABLE IF EXISTS " + tables.studentTable);
        db.execSQL(" DROP TABLE IF EXISTS " + tables.localTable);
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

    public List<Course> getCoursesByCareer(int careerid){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Course> courses= new ArrayList<Course>();
        Course course;

        String selection = tables.courseFields[3]+ "=?";
        String [] selectionargs = { String.valueOf(careerid) };
        Cursor cursor = db.query(tables.courseTable, tables.courseFields, selection, selectionargs, null , null, null);


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


    public String deleteCourse(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String regAfectados="Asignatura eliminada correctamente";
        int contador=0;
        //  if (verificarIntegridad(alumno,3)) {
        //     contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
        // }
        contador+=db.delete(tables.courseTable, tables.courseFields[0]+"='"+id+"'", null);
        db.close();
        return regAfectados;
    }

    public String editCourse (Course course){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] id = { String.valueOf(course.getId()) };
        ContentValues cv = new ContentValues();
        cv.put(tables.courseFields[1], course.getCodeCourse());
        cv.put(tables.courseFields[2], course.getCourse());
        cv.put(tables.courseFields[3], course.getCarrerId());
        db.update(tables.courseTable, cv, tables.courseFields[0]+" = ?", id);
        return "Registro Actualizado Correctamente";
    }

    public Course getCourseByCode(String code){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] codeCourse = {code};
        Cursor cursor = db.query(tables.courseTable, tables.courseFields, tables.courseFields[1]+" = ?",
                codeCourse , null, null, null);
        Course course = new Course();
        if(cursor.moveToFirst()){
            course.setId(cursor.getInt(0));
            course.setCodeCourse(cursor.getString(1));
            course.setCourse(cursor.getString(2));
            course.setCarrerId(cursor.getInt(3));
            db.close();
            return course;
        }else{
            db.close();
            return course;
        }
    }


    public Course getCourse(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] codeCourse = {id};
        Cursor cursor = db.query(tables.courseTable, tables.courseFields, tables.courseFields[0]+" = ?",
                codeCourse , null, null, null);
        Course course = new Course();
        if(cursor.moveToFirst()){
            course.setId(cursor.getInt(0));
            course.setCodeCourse(cursor.getString(1));
            course.setCourse(cursor.getString(2));
            course.setCarrerId(cursor.getInt(3));
            db.close();
            return course;
        }else{
            db.close();
            return course;
        }
    }

    //METODOS PARA ASIGNATURA

    //METODOS PARA LOCAL

    public List<Local> getLocals(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Local> locals= new ArrayList<Local>();
        Local local;
        Cursor cursor = db.query(tables.localTable, tables.localFields, null, null, null , null, null);

        while(cursor.moveToNext()){
            local  = new Local();
            local.setId(cursor.getInt(0));
            local. setLocal(cursor.getString(1));
            locals.add(local);
        }
        db.close();
        return locals;
    }


    public Local getLocal(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] data = {id};
        Cursor cursor = db.query(tables.localTable, tables.localFields, tables.localFields[0]+" = ?",
                data , null, null, null);
        Local local = new Local();
        if(cursor.moveToFirst()){
            local.setId(cursor.getInt(0));
            local.setLocal(cursor.getString(1));
            db.close();
            return local;
        }else{
            db.close();
            return local;
        }
    }


    public String addLocal(Local local){
        SQLiteDatabase db = this.getWritableDatabase();
        String message="";
        long contador=0;
        ContentValues Values = new ContentValues();
        Values.put(tables.localFields[1], local.getLocal() );
        contador=db.insert(tables.localTable, null, Values);

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
    //METODOS PARA LOCAL

    //METODOS PARA ALUMNO

    public Student getStudent(String code){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] carnet = {code};
        Cursor cursor = db.query(tables.studentTable, tables.studentFields, tables.studentFields[0]+" = ?",
                carnet , null, null, null);
        Student student= new Student();
        if(cursor.moveToFirst()){
            student.setCarnet(cursor.getString(0));
            student.setName(cursor.getString(1));
            student.setCareerId(cursor.getInt(2));
            db.close();
            return student;
        }else{
            db.close();
            return student;
        }
    }


    public String addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        String message="";
        long contador=0;
        ContentValues Values = new ContentValues();
        Values.put(tables.studentFields[0], student.getCarnet());
        Values.put(tables.studentFields[1], student.getName() );
        Values.put(tables.studentFields[2], student.getCareerId() );
        contador=db.insert(tables.studentTable, null, Values);

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
    //METODOS PARA ALUMNO

    //LLENADO DE INFORMACION

    public String dbInsertData(){
        int controller = 0;
        String Message = "Datos ingresados correctamente";
        SQLiteDatabase db = this.getWritableDatabase();
        int v = db.getVersion();
        this.onUpgrade(db,v, v++ );
        if(controller ==0){
            List<Career> careers = new ArrayList<>();
            careers = Help.carreersDB();

            List<Course> courses = new ArrayList<>();
            courses = Help.courseDB();

            List<Local> locals = new ArrayList<>();
             locals = Help.localDB();

            List<Student> students = new ArrayList<>();
            students = Help.studentsDB();

            for (int i = 0; i <careers.size() ; i++) {
                Career c = new Career();
                c = careers.get(i);
                this.addCareer(c);
            }

            for (int i = 0; i <courses.size() ; i++) {
                Course c = new Course();
                c = courses.get(i);
                this.addCourse(c);
            }

            for (int i = 0; i <locals.size() ; i++) {
                Local c = new Local();
                c = locals.get(i);
                this.addLocal(c);
            }

            for (int i = 0; i <students.size() ; i++) {
                Student c = new Student();
                c = students.get(i);
                this.addStudent(c);
            }
            controller++;

        }else{
            Message = "Ya se ha llenado la base de datos";
        }

        return  Message;
    }
    //LLENADO DE INFORMACION


}