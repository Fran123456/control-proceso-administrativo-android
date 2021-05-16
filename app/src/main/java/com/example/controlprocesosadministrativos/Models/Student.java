package com.example.controlprocesosadministrativos.Models;

public class Student {

    private String carnet, name;
    private int careerId;


    public Student() {
    }

    public Student(String carnet, String name, int careerId) {

        this.carnet = carnet;
        this.name = name;
        this.careerId = careerId;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCareerId() {
        return careerId;
    }

    public void setCareerId(int careerId) {
        this.careerId = careerId;
    }
}
