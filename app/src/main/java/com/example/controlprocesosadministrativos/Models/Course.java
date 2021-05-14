package com.example.controlprocesosadministrativos.Models;

public class Course {
    private int id, carrerId;
    private String course, codeCourse;

    public Course() {
    }

    public Course(int id, int carrerId, String course, String codeCourse) {
        this.id = id;
        this.carrerId = carrerId;
        this.course = course;
        this.codeCourse = codeCourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarrerId() {
        return carrerId;
    }

    public void setCarrerId(int carrerId) {
        this.carrerId = carrerId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCodeCourse() {
        return codeCourse;
    }

    public void setCodeCourse(String codeCourse) {
        this.codeCourse = codeCourse;
    }
}
