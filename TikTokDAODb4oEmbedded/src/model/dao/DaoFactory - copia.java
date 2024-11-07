package model.dao;

import db.DB;
import model.dao.impl.VideoDaoJDBC;

public class DaoFactory {
	
	public static VideoDaoJDBC createVideoDao() {
		return new VideoDaoJDBC(DB.getConnection());
	}
}
