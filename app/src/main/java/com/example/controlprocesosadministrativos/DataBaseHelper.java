package com.example.controlprocesosadministrativos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.controlprocesosadministrativos.Models.Career;
import com.example.controlprocesosadministrativos.Tables.Tables;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CONTROL_PROCESO_ADMINISTRATIVO";
    private Tables tables;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+tables.userTable + "("+tables.userFields[0]+" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " "+tables.userFields[1]+" TEXT ," +
                ""+tables.userFields[2]+" TEXT , "+tables.userFields[3]+" TEXT )");

        db.execSQL("CREATE TABLE IF NOT EXISTS "+tables.careerTable + "("+tables.careerFields[0]+" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " "+tables.careerFields[1]+" TEXT ," +
                ""+tables.careerFields[2]+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + tables.userTable);
        db.execSQL(" DROP TABLE IF EXISTS " + tables.careerTable);
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
            user.setNombre(cursor.getString(1));
            user.setUsuario(cursor.getString(2));
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
         return regAfectados;
     }

    //METODOS PARA CARRERA
}