package com.example.model.dao;

import java.util.List;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.example.model.entities.Casa;
import com.example.model.entities.Ciudad;
import com.example.model.entities.Huesped;

public class CasaDAO {
    
    // Método para insertar una casa
    public void insertarCasa(Casa casa, ObjectContainer db) {
        db.store(casa);
        System.out.println("Casa insertada: " + casa.getDireccion());
    }
    
    // Método para obtener una casa por dirección
    public Casa obtenerCasaPorDireccion(String direccion, ObjectContainer db) {
        Casa ejemplo = new Casa();
        ejemplo.setDireccion(direccion);
        ObjectSet<Casa> result = db.queryByExample(ejemplo);
        
        return result.hasNext() ? result.next() : null;
    }

    public List<Casa> obtenerCasaMasNumHabitaciones(int numHabitaciones, ObjectContainer db){
        List<Casa> casas = db.query(new Predicate<Casa>() {
        @Override
        public boolean match(Casa casa) {
            return casa.getNumHabitaciones() > numHabitaciones;
        }
        });
        for (Casa casa : casas) {
            System.out.println(casa);
        }
        db.close();
        return casas;
    }


    public List<Casa> obtenerCasasConCP(String cp, ObjectContainer db){
        List<Casa> casas = db.query(new Predicate<Casa>() {
            @Override
            public boolean match(Casa casa) {
                return casa.getCiudad().getCodigoPostal().equals(cp);
    }});
    for (Casa casa : casas) {
        System.out.println(casa);
    }
    db.close();
    return casas;
}

    public void cambiarNumPersonas(int numPersona, ObjectContainer db){
        List<Casa> casas = db.query(new Predicate<Casa>() {
        @Override
        public boolean match(Casa casa) {
        return casa.getNumHabitaciones() > 5;
        }
        });
        for (Casa casa : casas) {
        casa.setNumPersonas(numPersona);
        db.store(casa);
        }
        db.close();
}

    public void eliminarHabitacionesConmenorNumHabitaciones(int numHabitaciones,ObjectContainer db){
            List<Casa> casas = db.query(new Predicate<Casa>() {
            @Override
            public boolean match(Casa casa) {
            return casa.getNumHabitaciones()<numHabitaciones;
            }
            });
            for (Casa casa : casas) {
                db.delete(casa);
            }
            db.close();
    }

    public void encontrarCasasPorCPYnumPersonas(String cp,int numPersonas,ObjectContainer db){
        List<Casa> casas = db.query(new Predicate<Casa>() {
            @Override
            public boolean match(Casa casa) {
            return casa.getCiudad().getCodigoPostal().equals(cp)
            && casa.getNumPersonas() > numPersonas;
            }
            });
            for (Casa casa : casas) {
            System.out.println(casa);
            }
            db.close();
    }

    public void BuscarCasasConHuespedMenorDeEdad(int edad,ObjectContainer db){
        List<Casa> casas = db.query(new Predicate<Casa>() {
            @Override
            public boolean match(Casa casa) {
            return casa.getHuespeds().stream().anyMatch(huesped -> huesped.getEdad() < edad);
            }
            });
            for (Casa casa : casas) {
            System.out.println("Dirección: " + casa.getDireccion());
            db.close();
            }
            
    }
    public void AumentarCapacidadCasasPorMayorEdad(int edad,ObjectContainer db){
        List<Casa> casas = db.query(new Predicate<Casa>() {
            @Override
            public boolean match(Casa casa) {
            return casa.getHuespeds().stream().anyMatch(huesped -> huesped.getEdad() > edad);
            }
            });
            for (Casa casa : casas) {
                casa.setNumPersonas(8);
                db.store(casa);
            }
            db.close();
        }
    
    public void EliminarCasasPorCiudadYNUmHabitaciones(int numHabitaciones, String ciudad,ObjectContainer db){
        List<Casa> casas = db.query(new Predicate<Casa>() {
            @Override
            public boolean match(Casa casa) {
            return casa.getCiudad().getCiudad().equals(ciudad) && casa.getNumHabitaciones() < numHabitaciones;
            }
            });
            for (Casa casa : casas) {
                db.delete(casa);
            }
            db.close();
    }
    
    
}

    






}

