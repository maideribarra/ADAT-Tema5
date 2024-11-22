package com.example.model.dao;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.example.model.entities.Casa;
import com.example.model.entities.Huesped;

public class HuespedDAO {
    
    // Método para insertar un huésped
    public void insertarHuesped(Huesped huesped, ObjectContainer db) {
        db.store(huesped);
        System.out.println("Huésped insertado: " + huesped.getNombre());
    }
    
    // Método para obtener un huésped por nombre
    public Huesped obtenerHuespedPorNombre(String nombre, ObjectContainer db) {
        Huesped ejemplo = new Huesped();
        ejemplo.setNombre(nombre);
        ObjectSet<Huesped> result = db.queryByExample(ejemplo);
        
        return result.hasNext() ? result.next() : null;
    }

    public List<Huesped> obtenerHuespedPorEdad(int edad, ObjectContainer db) {
        Huesped ejemplo = new Huesped();
        ejemplo.setEdad(edad);
        ObjectSet<Huesped> result = db.queryByExample(ejemplo);
        
        return result;
    }

    public void cambiarCorreoHuesped(String correo, String nombre, ObjectContainer db){
        List<Huesped> huespedes = db.query(new Predicate<Huesped>() {
        @Override
        public boolean match(Huesped huesped) {
        return huesped.getNombre().equals(nombre);
        }
        });
        for (Huesped huesped : huespedes) {
            huesped.setEmail(correo);
            db.store(huesped);
        }
        db.close();
}

public void eliminarHuespedConmenorEdad(int edad,ObjectContainer db){
    List<Huesped> huespedes = db.query(new Predicate<Huesped>() {
    @Override
    public boolean match(Huesped huesped) {
    return huesped.getEdad()<edad;
    }
    });
    for (Huesped huesped : huespedes) {
        db.delete(huesped);
    }
    db.close();
}

public void encontrarHuespedesPorCiudadYNumHabitaciones(ObjectContainer db,int numHabitaciones,String ciudad){
    List<Casa> casas = db.query(new Predicate<Casa>() {
        @Override
        public boolean match(Casa casa) {
            return casa.getHuespeds() != null
            && casa.getNumHabitaciones() > numHabitaciones
            && casa.getCiudad().getCiudad().equals(ciudad);
        }
        });
        ArrayList<Huesped> arrHuespeds=new ArrayList<Huesped>();
        for (Casa casa : casas) {
            arrHuespeds.addAll(casa.getHuespeds());
            }
            ArrayList<Huesped> arrHuespedsSinDuplicados=new ArrayList<>(new LinkedHashSet<>(arrHuespeds))
        for (Huesped huesped:arrHuespedsSinDuplicados)
            System.out.println("Nombre: " + huesped.getNombre() + ", Mail: " + huesped.getEmail());
           
        db.close();
    
}
    public void CambiarNombreHuespedMenorDeEdad(String nombre,ObjectContainer db){
        List<Huesped> huespedes = db.query(new Predicate<Huesped>() {
        @Override
        public boolean match(Huesped huesped) {
            return huesped.getEdad() < 18;
        }
        });
        for (Huesped huesped : huespedes) {
            huesped.setNombre("Menor de Edad");
            db.store(huesped);
        }
    }

    public void EliminarHuespedPorCiudadYNUmpersonas(int numPersonas, String ciudad,ObjectContainer db){
        List<Casa> casas = db.query(new Predicate<Casa>() {
            @Override
            public boolean match(Casa casa) {
            return casa.getCiudad().getCiudad().equals(ciudad) && casa.getNumPersonas()<numPersonas;
            }
            });
            ArrayList<Huesped> arrHuespeds=new ArrayList<Huesped>();
            for (Casa casa : casas) {
                arrHuespeds.addAll(casa.getHuespeds());
                }
                ArrayList<Huesped> arrHuespedsSinDuplicados=new ArrayList<>(new LinkedHashSet<>(arrHuespeds));
            for (Huesped huesped:arrHuespedsSinDuplicados){
                db.delete(huesped);
            }
            
            db.close();
    }

    
    
    
}

