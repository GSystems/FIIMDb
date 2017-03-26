package eu.ubis.fiimdb.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import eu.ubis.fiimdb.model.Genre;

public class GenreService {
	
	private EntityManager entityManager;
	
	GenreService() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("fiimdb");
		entityManager = emFactory.createEntityManager();
	}
	
	public List<Genre> getGenres() {
		List<Genre> genres = new ArrayList<Genre>();
		
		return genres;
	}

}
