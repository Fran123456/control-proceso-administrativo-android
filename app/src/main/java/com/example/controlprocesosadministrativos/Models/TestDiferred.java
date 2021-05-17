package com.example.controlprocesosadministrativos.Models;

public class TestDiferred {

    private int id;
    private String date;
    private String time;
    private int localId;
    private String studentId;
    private int courseId;

    public TestDiferred() {

    }

    public TestDiferred(int id, String date, String time, int localId, String studentId, int couseId) {

        this.id = id;
        this.date = date;
        this.time = time;
        this.localId = localId;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLocalId() {
        return localId;
    }

    public void setLocalId(int localId) {
        this.localId = localId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
