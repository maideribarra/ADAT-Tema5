package com.example.model.entities;

public class Ciudad {
    private String ciudad;
    private String codigoPostal;

    // Constructor vacío
    public Ciudad() {}

    // Constructor con parámetros
    public Ciudad( String ciudad, String codigoPostal) {
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
    }

    // Getters y Setters




    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}

