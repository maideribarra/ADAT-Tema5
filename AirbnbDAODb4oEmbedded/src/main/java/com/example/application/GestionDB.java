package com.example.application;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import com.example.model.entities.*;
import com.db4o.Db4o;
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
    public void insertarDatos(){
        ObjectContainer db = new DB().getConnection();
        // Ciudades
        Ciudad barcelona = new Ciudad();
        barcelona.setCiudad("Barcelona");    
        barcelona.setCodigoPostal("08001");
     
        Ciudad madrid = new Ciudad();
        madrid.setCiudad("Madrid");
        madrid.setCodigoPostal("28001");

        Ciudad valencia = new Ciudad();
        valencia.setCiudad("Valencia");
        valencia.setCodigoPostal("46001");

        Ciudad sevilla = new Ciudad();
        sevilla.setCiudad("Sevilla");
        sevilla.setCodigoPostal("41001");


        Ciudad bilbao = new Ciudad();
        sevilla.setCiudad("Bilbao");
        sevilla.setCodigoPostal("10789");

        // Casas
        Casa casa1 = new Casa();
        casa1.setNumPersonas(4);
        casa1.setNumHabitaciones(3);
        casa1.setDireccion("Calle Mayor, 10");
        casa1.setCiudad(madrid);

        Casa casa2 = new Casa();
        casa2.setNumPersonas(6);
        casa2.setNumHabitaciones(5);
        casa2.setDireccion("Avenida de las Flores, 22");
        casa2.setCiudad(valencia);

        Casa casa3 = new Casa();
        casa3.setNumPersonas(2);
        casa3.setNumHabitaciones(1);
        casa3.setDireccion("Plaza España, 5");
        casa3.setCiudad(sevilla);

        Casa casa4 = new Casa(); 
        casa4.setNumPersonas(5);
        casa4.setNumHabitaciones(4); 
        casa4.setDireccion("Calle Gran Vía, 5");
        casa4.setCiudad(madrid);

        Casa casa5 = new Casa(); 
        casa5.setNumPersonas(3);
        casa5.setNumHabitaciones(2);
        casa5.setDireccion("Calle del Mar, 10");
        casa5.setCiudad(valencia); 

        Casa casa6 = new Casa(); 
        casa6.setNumPersonas(8);
        casa6.setNumHabitaciones(5);
        casa6.setDireccion("Avenida Diagonal, 22"); 
        casa6.setCiudad(barcelona);

        Casa casa7 = new Casa(); 
        casa7.setNumPersonas(2);
        casa7.setNumHabitaciones(1);
        casa7.setDireccion("Calle Los Olivos, 15");
        casa7.setCiudad(sevilla);

      
        // Huéspedes
        Huesped huesped1 = new Huesped();
        huesped1.setEdad(25);
        huesped1.setNombre("Carlos Pérez");
        huesped1.setEmail("carlos.perez@gmail.com");
       

        Huesped huesped2 = new Huesped();
        huesped2.setEdad(17);
        huesped2.setNombre("Ana López");
        huesped2.setEmail("ana.lopez@gmail.com");
        

        Huesped huesped3 = new Huesped();
        huesped3.setEdad(35);
        huesped3.setNombre("María García");
        huesped3.setEmail("maria.garcia@gmail.com");
        

        // Asociar huéspedes a casas
        casa1.setHuespeds((ArrayList<Huesped>) Arrays.asList(huesped1));
        casa2.setHuespeds((ArrayList<Huesped>)Arrays.asList(huesped2));
        casa3.setHuespeds((ArrayList<Huesped>)Arrays.asList(huesped3));

        // Guardar en la base de datos
        db.store(madrid);
        db.store(valencia);
        db.store(sevilla);
        db.store(bilbao);

        db.store(casa1);
        db.store(casa2);
        db.store(casa3);

        db.store(huesped1);
        db.store(huesped2);
        db.store(huesped3);

        db.close();
        System.out.println("Datos insertados correctamente.");
    }
}


