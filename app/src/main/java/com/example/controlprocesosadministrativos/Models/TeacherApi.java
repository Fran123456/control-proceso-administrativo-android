package com.example.controlprocesosadministrativos.Models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TeacherApi {

    @GET("ws_docente_select.php")
    public Call<List<Teacher>> contenido();

    @GET("ws_docente_insert.php")
    public Call<Success> agregar(@Query("nombre") String nombre, @Query("apellido")String apellido);

    @GET("ws_docente_delete.php")
    public Call<Success> eliminar(@Query("id") int id);
}
