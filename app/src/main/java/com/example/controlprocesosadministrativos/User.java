package com.example.controlprocesosadministrativos;

public class User {
    int id;
    String nombre, usuario, password;

    public User(int id, String nombre, String usuario, String password) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
    }

    public User(){

    }


    public Boolean isNull(){
        if(nombre.equals("")&& usuario.equals("")&&password.equals("")){
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
