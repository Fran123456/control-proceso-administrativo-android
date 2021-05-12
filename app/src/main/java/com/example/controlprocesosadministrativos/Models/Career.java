package com.example.controlprocesosadministrativos.Models;

public class Career {
    private int id;
    private String career, codeCareer;

    public Career() {
    }

    public Career(int id, String codeCareer,String career) {
        this.id = id;
        this.career = career;
        this.codeCareer = codeCareer;
    }

    public String getCodeCareer() {
        return codeCareer;
    }

    public void setCodeCareer(String codeCareer) {
        this.codeCareer = codeCareer;
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
