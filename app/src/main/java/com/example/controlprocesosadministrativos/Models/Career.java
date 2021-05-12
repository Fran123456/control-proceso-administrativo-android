package com.example.controlprocesosadministrativos.Models;

public class Career {
    private int id;
    private String career;

    public Career() {
    }

    public Career(int id, String career) {
        this.id = id;
        this.career = career;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
}
