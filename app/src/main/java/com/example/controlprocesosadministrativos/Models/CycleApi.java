package com.example.controlprocesosadministrativos.Models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CycleApi {

    @GET("ws_ciclo_select.php")
    public Call<List<Cycle>> contenido();

    @GET("ws_ciclo_delete.php")
    public Call<Success> eliminar(@Query("id") int id);
}
