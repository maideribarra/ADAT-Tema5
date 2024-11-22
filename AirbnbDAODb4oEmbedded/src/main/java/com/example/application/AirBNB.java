package com.example.application;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AirBNB{
    public static void main(String[]args){
        GestionDB gdb= new GestionDB();
        gdb.cargarDatos("C:\\Users\\maider\\Desktop\\Ciudad_jardin\\workspace\\Tema5ADAT\\AirbnbDAODb4oEmbedded\\airbnb_data_coherent.csv");
        gdb.insertarDatos();

    }

   
}  
