package com.example.model.entities;

import java.util.ArrayList;

public class Casa {
    private String direccion;
    private int numHabitaciones;
    private int numPersonas;
    private Ciudad ciudad;
    private ArrayList<Huesped> arrHuesped;

    // Constructor vacío
    public Casa() {}

    // Constructor con parámetros
    public Casa( String direccion, int numHabitaciones, int numPersonas) {
        this.direccion = direccion;
        this.numHabitaciones = numHabitaciones;
        this.numPersonas = numPersonas;
        this.arrHuesped=new ArrayList<Huesped>();
    }



    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Ciudad getCiudad() {
        return this.ciudad;
    }



    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public void setHuesped(Huesped huesped) {
        // TODO Auto-generated method stub
        this.arrHuesped.add(huesped);
    }

    public void setCiudad(Ciudad ciudad) {
        // TODO Auto-generated method stub
        this.ciudad=ciudad;
    }

    public ArrayList<Huesped> getHuespeds(){
        return this.arrHuesped;
    }

    public void setHuespeds(ArrayList<Huesped> arrHuesped){
        this.arrHuesped=arrHuesped;
    }
}
