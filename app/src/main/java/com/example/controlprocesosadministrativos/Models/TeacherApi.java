package com.example.controlprocesosadministrativos.Models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TeacherApi {

    @GET("ws_docente_select.php")
    public Call<List<Teacher>> contenido();
}
