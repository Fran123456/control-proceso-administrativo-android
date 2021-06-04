package com.example.controlprocesosadministrativos.Models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CycleApi {

    @GET("ws_ciclo_select.php")
    public Call<List<Cycle>> contenido();
}
