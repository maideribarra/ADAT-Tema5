package com.example.application;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;


import com.example.model.dao.VideoDAO;
import com.example.model.dao.impl.VideoDaoDb4o;
import com.example.model.entities.*;
import com.db4o.ObjectContainer;
import com.example.*;
import com.example.db.DB;



public class fromcsvTodb {
    public static  VideoDAO videoDao=null;
     
    public static void eliminarVideosCortaDuracion(float duracionMinima) {
        videoDao.deleteByDuracion(duracionMinima);
        // SQL para eliminar videos cuya duración es menor que la duracionMinima
        
    }

    public static void insertarVideos(ArrayList<Video> listaVideos) {
        // SQL para insertar un video en la tabla            
        for (Video video : listaVideos) {
            System.out.println("insertar");
            System.out.println(video);
            videoDao.insert(video);
        }

        System.out.println("Videos insertados correctamente.");       
    }

    public static void actualizarLikesUsuarios(ArrayList<Integer> likes) {
        for (int i = 0; i < likes.size(); i++) {
            String nombreUsuario = "@usuario" + (i + 1);  // Generar nombre de usuario
            int numeroLikes = likes.get(i);  // Obtener el número de likes para el usuario
            videoDao.update(nombreUsuario,numeroLikes);           
            
            System.out.println("Likes actualizados para " + nombreUsuario + ": " + numeroLikes);
        }
    }

    public static void main(String[] args) {
        DB db=new DB();
        videoDao=new VideoDaoDb4o(db.getConnection());
        String rutaCSV="./TikTok2.csv";
        ArrayList<Video> arrVideos=TikTok.CargarVideos(rutaCSV);
        System.out.println("size"+arrVideos.size());
        insertarVideos(arrVideos);
        Random random = new Random();
        ArrayList<Integer> likes = new ArrayList<>();
        eliminarVideosCortaDuracion(20);
        for (int i = 0; i < 30; i++) {
            int randomValue = random.nextInt(1001); // Genera un número aleatorio entre 0 y 1000
            likes.add(randomValue);
        }
        actualizarLikesUsuarios(likes);
        videoDao.actualizarLikesUsuariosProcedimiento(likes);

        /**
         * DELIMITER //

CREATE PROCEDURE subida_likes (IN likes_in INT, IN usuario VARCHAR(255)) 
BEGIN
    UPDATE tiktok.video 
    SET likes = likes + likes_in 
    WHERE autor = usuario;
END //

DELIMITER ;
callableStatement = connection.prepareCall("{CALL subida_likes(?, ?)}");
        */



}
}
