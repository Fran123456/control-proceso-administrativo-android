package com.example.controlprocesosadministrativos.Utility;

public class Menu {

    public Menu() {
    }

    public Menu(String title, String description, int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    private String title, description;
    private int image;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
