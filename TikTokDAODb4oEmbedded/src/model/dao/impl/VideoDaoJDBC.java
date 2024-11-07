package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import db.DB;
import db.DbException;
import model.dao.VideoDAO;
import model.entities.Video;

public class VideoDaoJDBC implements VideoDAO{
	
	private Connection connection;
	
	public VideoDaoJDBC(Connection connection) {
		this.connection = connection;
		createTable();
	}

	@Override
	public void insert(Video video) {
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		
		try {
			statement = connection.prepareStatement( "INSERT INTO  TikTokDAO.video (nombre_video, duracion, autor, likes, fecha_subida, etiqueta_1, etiqueta_2, etiqueta_3) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

			statement.setString(1, video.nombre_video);
			statement.setFloat(2, video.duracion);
			statement.setString(3, video.autor);
			statement.setInt(4, video.likes);
			statement.setInt(5, video.fecha_subida);
			statement.setString(6, video.etiquetas[0]);
			statement.setString(7, video.etiquetas[1]);
			statement.setString(8, video.etiquetas[2]);
	
			
			int rowsAffedcted = statement.executeUpdate();
			
		
			
		
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(statement);
		}
		
	}
	@Override
	public void createTable() {
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			String sql1 = "CREATE TABLE IF NOT EXISTS TikTokDAO.video (" +
			"nombre_video VARCHAR(255)," +
			"duracion FLOAT," +
			"autor VARCHAR(255)," +
			"likes INT," +
			"fecha_subida INT," +
			"etiqueta_1 VARCHAR(255)," +
			"etiqueta_2 VARCHAR(255)," +
			"etiqueta_3 VARCHAR(255)" +
			");";					
						
			statement.executeUpdate(sql1);

			
		}
		catch(Exception e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(statement);
		}
		
	}

	@Override
	public void update(String nombreUsuario, int likes) {
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(
				"UPDATE  TikTokDAO.video SET likes = ? WHERE autor = ?"
				);
			
			statement.setInt(1, likes);
			statement.setString(2, nombreUsuario);
			
			statement.executeUpdate();
	
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(statement);
		}
		
	}

	@Override
	public void deleteByDuracion(float duracion) {
		PreparedStatement statement = null;
		
		try {
			statement=connection.prepareStatement( "DELETE FROM  TikTokDAO.video WHERE duracion < ?");
		
			statement.setDouble(1, duracion);
			
			statement.executeUpdate();
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(statement);
		}
	}

	@Override
	public void actualizarLikesUsuariosProcedimiento(ArrayList<Integer> likes) {
        try{
            CallableStatement callableStatement = connection.prepareCall("{CALL tiktok.subida_likes(?, ?)}");
            // Recorrer el ArrayList de likes y actualizar para cada usuario
            for (int i = 0; i < likes.size(); i++) {
                String nombreUsuario = "@usuario" + (i + 1);  // Generar nombre de usuario
                int numeroLikes = likes.get(i);  // Obtener el número de likes para el usuario
                
                // Asignar los valores al PreparedStatement
                callableStatement.setInt(1, numeroLikes);
                callableStatement.setString(2, nombreUsuario);
                
                // Ejecutar el UPDATE
                callableStatement.execute();
                
                System.out.println("Likes actualizados para " + nombreUsuario + ": " + numeroLikes);
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar los likes: " + e.getMessage());
        }
    }


	

	


	
}
