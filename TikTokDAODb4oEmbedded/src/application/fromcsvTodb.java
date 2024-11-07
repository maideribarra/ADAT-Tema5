import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import model.dao.DaoFactory;
import model.dao.VideoDAO;
import model.entities.*;



public class fromcsvTodb {
    public static  VideoDAO videoDao=null;
     
    public static void eliminarVideosCortaDuracion(float duracionMinima) {
        videoDao.deleteByDuracion(duracionMinima);
        // SQL para eliminar videos cuya duración es menor que la duracionMinima
        
    }

    public static void insertarVideos(ArrayList<Video> listaVideos) {
        // SQL para insertar un video en la tabla            
        for (Video video : listaVideos) {
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
        videoDao = DaoFactory.createVideoDao();
        String rutaCSV="./TikTokDAO/TikTok2.csv";
        TikTok tiktok=new TikTok();
        ArrayList<Video> arrVideos=tiktok.CargarVideos(rutaCSV);
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
