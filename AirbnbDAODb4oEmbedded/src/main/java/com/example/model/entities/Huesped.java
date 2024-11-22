package com.example.model.entities;

public class Huesped {
    private String nombre;
    private int edad;
    private String email;

    // Constructor vacío
    public Huesped() {}

    // Constructor con parámetros
    public Huesped( String nombre, int edad, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   
}

