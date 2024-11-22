package com.example.application;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import com.example.model.entities.*;
import com.db4o.ObjectContainer;
import com.example.*;
import com.example.db.DB;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.example.model.dao.CasaDAO;
import com.example.model.dao.HuespedDAO;
import com.example.model.dao.CiudadDAO;
import com.example.model.entities.Casa;
import com.example.model.entities.Huesped;
import com.example.model.entities.Ciudad;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class GestionDB {

    public void cargarDatos(String ruta) {
        CiudadDAO ciudadDAO = new CiudadDAO();
        CasaDAO casaDAO = new CasaDAO();
        HuespedDAO huespedDAO = new HuespedDAO();
        ObjectContainer session = new DB().getConnection();
        
        try (CSVReader reader = new CSVReader(new FileReader(ruta))) {
            
            // Leer todas las líneas del archivo CSV
            List<String[]> lineas = reader.readAll();
            lineas.remove(0); // Remover cabecera

            // Procesar cada línea del archivo CSV
            for (String[] linea : lineas) {
                System.out.println("Procesando línea: " + linea[0]);
                
                // Buscar o crear Ciudad
                Ciudad ciudad = ciudadDAO.obtenerCiudadPorNombre(linea[3], session);
                if (ciudad != null) {
                    System.out.println("Ciudad encontrada: " + ciudad.getCiudad());
                } else {
                    System.out.println("Ciudad no encontrada. Creando nueva ciudad.");
                    ciudad = new Ciudad(linea[3], linea[4]); // Crear ciudad con nombre y código postal
                }
                
                // Buscar o crear Casa
                Casa casa = casaDAO.obtenerCasaPorDireccion(linea[0], session);
                if (casa != null) {
                    System.out.println("Casa encontrada: " + casa.getDireccion());
                } else {
                    System.out.println("Casa no encontrada. Creando nueva casa.");
                    casa = new Casa(linea[0], linea[1].equals("")? 0 :(int)(Double.parseDouble(linea[1])), linea[2].equals("")? 0 :(int)(Double.parseDouble(linea[2])));
                }
                
                // Buscar o crear Huesped
                Huesped huesped = huespedDAO.obtenerHuespedPorNombre(linea[0], session);
                if (huesped != null) {
                    System.out.println("Huésped encontrado: " + huesped.getNombre());
                } else {
                    System.out.println("Huésped no encontrado. Creando nuevo huésped.");
                    huesped = new Huesped(linea[5], linea[6].equals("")? 0 :(int)Double.parseDouble(linea[6]), linea[7]);
                }

                // Asociar entidades
                casa.setCiudad(ciudad);
                casa.setHuesped(huesped);
                
                // Guardar entidades en la base de datos
                ciudadDAO.insertarCiudad(ciudad, session);
                casaDAO.insertarCasa(casa, session);
                huespedDAO.insertarHuesped(huesped, session);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void findHuespedesByCiudadCasa(String nombreCiudad, String direccionCasa) {
        HuespedDAO huespedDAO = new HuespedDAO();
        //huespedDAO.findHuespedesByCiudadCasa(nombreCiudad, direccionCasa);
    }
}
