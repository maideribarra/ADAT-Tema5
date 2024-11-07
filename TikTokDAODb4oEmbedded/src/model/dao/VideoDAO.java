package model.dao;

import java.util.ArrayList;
import model.entities.Video;


public interface VideoDAO {

	void insert(Video video);

	void createTable();

	

	void update(String nombreUsuario, int likes);

	void deleteByDuracion(float duracion);

	void actualizarLikesUsuariosProcedimiento(ArrayList<Integer> likes);


}
