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
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

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


}
