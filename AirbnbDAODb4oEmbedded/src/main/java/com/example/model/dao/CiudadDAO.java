package com.example.model.dao;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.example.model.entities.Casa;
import com.example.model.entities.Ciudad;
import com.example.model.entities.Huesped;

public class CiudadDAO {
    
    // Método para insertar una ciudad
    public void insertarCiudad(Ciudad ciudad, ObjectContainer db) {
        db.store(ciudad);
        System.out.println("Ciudad insertada: " + ciudad.getCiudad());
    }
    
    // Método para obtener una ciudad por nombre
    public Ciudad obtenerCiudadPorNombre(String nombre, ObjectContainer db) {
        Ciudad ejemplo = new Ciudad();
        ejemplo.setCiudad(nombre);
        ObjectSet<Ciudad> result = db.queryByExample(ejemplo);
        
        return result.hasNext() ? result.next() : null;
    }

    public void cambiarCPCiudad(String cp, String nombre, ObjectContainer db){
        List<Ciudad> ciudades = db.query(new Predicate<Ciudad>() {
        @Override
        public boolean match(Ciudad ciudad) {
        return ciudad.getCiudad().equals(nombre);
        }
        });
        for (Ciudad ciudad : ciudades) {
            ciudad.setCodigoPostal(cp);
            db.store(ciudad);
        }
        db.close();
}
    public void eliminarCiudadesSinCasas(ObjectContainer db){
        List<Ciudad> localidades = db.query(Ciudad.class);
        for (Ciudad localidad : localidades) {
            List<Casa> casas = db.query(new Predicate<Casa>() {
            @Override
            public boolean match(Casa casa) {
                return casa.getCiudad().equals(localidad);
            }
        });
        if (casas.isEmpty()) {
            db.delete(localidad);
        }
        }
        db.close();
    }
}

