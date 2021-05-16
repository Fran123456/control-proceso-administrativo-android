package com.example.controlprocesosadministrativos.Tables;

public class Tables {
    public static String userTable = "users";
    public static  String[] userFields = new String []{"id","username","email", "password"};

    public static String careerTable ="career";
    public static  String[] careerFields = new String []{"id","code_career","career"};

    public static String courseTable ="course";
    public static  String[] courseFields = new String []{"id","code_course","course","career_id"};

    public static String studentTable ="student";
    public static  String[] studentFields = new String []{"carnet","student","career_id"};

    public static String localTable ="local";
    public static  String[] localFields = new String []{"id","local"};

    public static String deferredTable ="deferred";
    public static  String[] deferredFields = new String []{"id","date","time","local_id","student_id"};
}