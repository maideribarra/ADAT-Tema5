package com.example.model.dao.impl;

import com.example.model.dao.VideoDAO;
import com.example.model.entities.Video;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.util.ArrayList;
import java.util.List;

public class VideoDaoDb4o implements VideoDAO {

    private ObjectContainer db;

    public VideoDaoDb4o(ObjectContainer db) {
        this.db = db;
    }

    // Método para insertar un video en la base de datos
    @Override
    public void insert(Video video) {
        try {
            db.store(video);  // Almacena el objeto en db4o
            db.commit();      // Confirma la transacción en db4o
            System.out.println("Video insertado con éxito en db4o.");
        } catch (Exception e) {
            db.rollback();
            e.printStackTrace();
        }
    }

    // Método para actualizar los likes de un usuario
    @Override
    public void update(String nombreUsuario, int likes) {
        try {
            Query query = db.query();
            query.constrain(Video.class);
            query.descend("autor").constrain(nombreUsuario);
            ObjectSet<Video> results = query.execute();

            if (!results.isEmpty()) {
                Video video = results.next();
                video.setLikes(likes); // Actualiza los likes
                db.store(video);       // Guarda el cambio en db4o
                db.commit();
                System.out.println("Likes actualizados para el usuario: " + nombreUsuario);
            }
        } catch (Exception e) {
            db.rollback();
            e.printStackTrace();
        }
    }

    // Método para eliminar videos con duración menor a un valor dado
    @Override
    public void deleteByDuracion(float duracion) {
        try {
            Query query = db.query();
            query.constrain(Video.class);
            query.descend("duracion").constrain(duracion).smaller();
            ObjectSet<Video> results = query.execute();

            for (Video video : results) {
                db.delete(video); // Elimina cada video que cumple con el criterio
            }
            db.commit();
            System.out.println("Videos eliminados con duración menor a " + duracion);
        } catch (Exception e) {
            db.rollback();
            e.printStackTrace();
        }
    }

    // Método para actualizar los likes de varios usuarios usando un procedimiento
    @Override
    public void actualizarLikesUsuariosProcedimiento(ArrayList<Integer> likes) {
        try {
            for (int i = 0; i < likes.size(); i++) {
                String nombreUsuario = "@usuario" + (i + 1); // Genera el nombre del usuario
                int numeroLikes = likes.get(i);

                Query query = db.query();
                query.constrain(Video.class);
                query.descend("autor").constrain(nombreUsuario);
                ObjectSet<Video> results = query.execute();

                if (!results.isEmpty()) {
                    Video video = results.next();
                    video.setLikes(video.getLikes() + numeroLikes); // Suma los likes
                    db.store(video); // Guarda el cambio en db4o
                }
            }
            db.commit();
            System.out.println("Likes actualizados para varios usuarios.");
        } catch (Exception e) {
            db.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void createTable() {
        throw new UnsupportedOperationException("El método 'createTable' no es necesario en db4o");
    }
}
