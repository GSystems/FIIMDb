package eu.ubis.fiimdb.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import eu.ubis.fiimdb.db.dao.GenreDao;
import eu.ubis.fiimdb.db.dao.MovieDao;
import eu.ubis.fiimdb.model.Movie;

public class MovieService {
	
	//GET ALL MOVIES WITH JPA
	@SuppressWarnings("unchecked")
	public List<Movie> getMovies(int pageNumber, int pageSize) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		List<Movie> movies = new ArrayList<Movie>();
		List<MovieDao> moviesDao = new ArrayList<MovieDao>();

		entityManager.getTransaction().begin();
		
		Query query = entityManager.createQuery("Select m From MovieDao m");
		query.setFirstResult((pageNumber-1) * pageSize);
		query.setMaxResults(pageSize);
		
		moviesDao = query.getResultList();
		entityManager.close();
		emf.close();
		for(MovieDao movie : moviesDao)
			movies.add(mapMovieDaoToMovie(movie));
		return movies;
	}
	
	@SuppressWarnings("unchecked")
	public List<Movie> getMoviesNoPagination() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		List<Movie> movies = new ArrayList<Movie>();
		List<MovieDao> moviesDao = new ArrayList<MovieDao>();

		entityManager.getTransaction().begin();
		
		Query query = entityManager.createQuery("Select m From MovieDao m");
		moviesDao = query.getResultList();
		entityManager.close();
		emf.close();
		for(MovieDao movie : moviesDao)
			movies.add(mapMovieDaoToMovie(movie));
		return movies;
	}
	
	//SEARCH WITH JPA
	@SuppressWarnings("unchecked")
	public List<Movie> search(String criteria, String value) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager  = emf.createEntityManager();
		List<Movie> movies = new ArrayList<Movie>();
		List<MovieDao> moviesDao = new ArrayList<MovieDao>();
		
		String query = "SELECT m FROM MovieDao m ";
		
		switch(criteria) {
		case "name":
			query += "WHERE m.name LIKE '%" + value + "%'";
			break;
		case "genre":
			query += "JOIN m.genres genre WHERE genre.type LIKE '%" + value + "%'";
			
			break;
		case "year":
			query += "WHERE EXTRACT(YEAR m.releaseDate) = " + Integer.parseInt(value);
			break;
		case "description":
			query += "WHERE m.description LIKE '%" + value + "%'";
			break;
		}
		
		Query searchQuery = entityManager.createQuery(query);
		entityManager.getTransaction().begin();
		
		moviesDao = searchQuery.getResultList();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();
		
		for(MovieDao movie : moviesDao) {
			if (!movies.contains(movie)) {
				movies.add(mapMovieDaoToMovie(movie));
			}
		}
		return movies;
	}
	
	//GET A MOVIE WITH JPA
	public Movie getMovieById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		MovieDao movieDao = entityManager.find(MovieDao.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();
		Movie movie = mapMovieDaoToMovie(movieDao);
		return movie;
	}
	
	//DELETE WITH JPA
	public void deleteMovie(int movieId) {
		MovieDao movieDao = new MovieDao();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		
		entityManager.getTransaction().begin();
		movieDao = entityManager.find(MovieDao.class, movieId);
		entityManager.remove(movieDao);
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();
	}
	
	//UPDATE WITH JPA
	public void updateMovie(Movie movie, int[] movieGenreIds, int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		MovieDao movieDao = new MovieDao();
		
		entityManager.getTransaction().begin();
		movieDao = entityManager.find(MovieDao.class, id);
		
		movieDao.setName(movie.getName());
		movieDao.setCasting(movie.getCasting());
		movieDao.setDescription(movie.getDescription());
		movieDao.setLength(movie.getLength());
		movieDao.setRating(movie.getRating());
		movieDao.setWriter(movie.getWriter());
		movieDao.setDirector(movie.getDirector());
		List<GenreDao> movieGenres = new ArrayList<>();
		for(int movieGenreId : movieGenreIds) {
			GenreDao movieGenre = new GenreDao();
			movieGenre.setId(movieGenreId);
			movieGenres.add(movieGenre);
		}
		movieDao.setGenres(movieGenres);		
		
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();
	}
	
	//INSERT WITH JPA
	public void insertMovie(Movie movie, int[] movieGenreIds) {
		MovieDao movieDao = mapMovieModelToDao(movie);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		List<GenreDao> movieGenres = new ArrayList<>();
		for(int movieGenreId : movieGenreIds) {
			GenreDao movieGenre = new GenreDao();
			movieGenre.setId(movieGenreId);
			movieGenres.add(movieGenre);
		}
		movieDao.setGenres(movieGenres);
		
		entityManager.getTransaction().begin();
		entityManager.persist(movieDao);
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();
	}
	
	private MovieDao mapMovieModelToDao(Movie movie) {
		MovieDao movieDao = new MovieDao();
		movieDao.setId(movie.getId());
		movieDao.setReleaseDate(movie.getReleaseDate());
		movieDao.setName(movie.getName());
		movieDao.setRating(movie.getRating());
		movieDao.setLength(movie.getLength());
		movieDao.setCasting(movie.getCasting());
		movieDao.setDirector(movie.getDirector());
		movieDao.setDescription(movie.getDescription());
		movieDao.setWriter(movie.getWriter());		
		return movieDao;
	}
	
	private Movie mapMovieDaoToMovie(MovieDao movieDao) {
		Movie movie = new Movie();
		movie.setId(movieDao.getId());
		movie.setReleaseDate(movieDao.getReleaseDate());
		movie.setName(movieDao.getName());
		movie.setRating(movieDao.getRating());
		movie.setLength(movieDao.getLength());
		movie.setCasting(movieDao.getCasting());
		movie.setDirector(movieDao.getDirector());
		movie.setWriter(movieDao.getWriter());
		if (movieDao.getDescription() != null) {
			movie.setDescription(movieDao.getDescription());
		} else {
			movie.setDescription("");
		}
		
		String genres = mapGenreEntityListToMovie(movieDao.getGenres());
		movie.setGenre(genres);
		return movie;
	}
	
	private String mapGenreEntityListToMovie(List<GenreDao> genreDao) {
		if (genreDao.size() <= 0) {
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (GenreDao ge : genreDao) {
			stringBuilder.append(ge.getType());
			if (genreDao.indexOf(ge) != genreDao.size() - 1) {
				stringBuilder.append(", ");
			}
		}
		return stringBuilder.toString();
	}
}