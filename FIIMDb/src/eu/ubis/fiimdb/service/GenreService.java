package eu.ubis.fiimdb.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import eu.ubis.fiimdb.db.dao.GenreDao;
import eu.ubis.fiimdb.model.Genre;

public class GenreService {
	
	private EntityManager entityManager;
	
	GenreService() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("fiimdb");
		entityManager = emFactory.createEntityManager();
	}
	
	public List<Genre> getGenres() {
		
		List<GenreDao> genreEntities = (List<GenreDao>) entityManager.createNamedQuery("getAllGenres").getResultList();
		
		List<Genre> genres = new ArrayList<Genre>();
		
		for (GenreDao genreEntity : genreEntities) {
			Genre genre = new Genre();
			genre.setId(genreEntity.getId());
			genre.setType(genreEntity.getType());
			
			genres.add(genre);
		}
		return genres;
	}
}
