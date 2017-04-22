package eu.ubis.fiimdb.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import eu.ubis.fiimdb.db.dao.DirectorDao;
import eu.ubis.fiimdb.db.dao.MovieDao;
import eu.ubis.fiimdb.model.Director;

public class DirectorService {
	
	public Director getDirectorByName(String name) {
		Director director = new Director();
		DirectorDao directorDao = new DirectorDao();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		directorDao = (DirectorDao) entityManager.createQuery("SELECT d FROM DirectorDao d WHERE d.name LIKE '"+ name +"'").getSingleResult();
		director = mapDirectorDaoToDirector(directorDao);
		return director;
	}
	
	public Director mapDirectorDaoToDirector(DirectorDao directorDao) {
		Director director = new Director();
		director.setName(directorDao.getName());
		director.setNationality(directorDao.getNationality());
		director.setAge(directorDao.getAge());
		String movie = mapMovieListToDirector(directorDao.getMovies());
		director.setMovie(movie);
		return director;
	}
	
	private String mapMovieListToDirector(List<MovieDao> movieDao) {
		if(movieDao.size() <= 0) {
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder();
		for(MovieDao md : movieDao) {
			stringBuilder.append(md.getName());
			if(movieDao.indexOf(md) != movieDao.size() - 1)
				stringBuilder.append(", ");
		}
		return stringBuilder.toString();
	}
}
