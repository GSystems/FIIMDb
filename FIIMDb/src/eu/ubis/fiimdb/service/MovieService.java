package eu.ubis.fiimdb.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import eu.ubis.fiimdb.db.dao.GenreDao;
import eu.ubis.fiimdb.db.dao.MovieDao;
import eu.ubis.fiimdb.db.entity.GenreEntity;
import eu.ubis.fiimdb.db.entity.MovieEntity;
import eu.ubis.fiimdb.db.repository.MovieRepository;
import eu.ubis.fiimdb.db.repository.RepositoryFactory;
import eu.ubis.fiimdb.model.Movie;

public class MovieService {
	
	public List<Movie> getMovies() {
		MovieRepository movieRepository = RepositoryFactory.getMovieRepository();
		List<MovieEntity> movieEntities = movieRepository.getAllMovies();

		List<Movie> movies = new ArrayList<Movie>();
		for (MovieEntity movieEntity : movieEntities) {
			Movie movie = mapMovieEntityToModel(movieEntity);
			movies.add(movie);
		}
		return movies;
	}

	public Movie mapMovieEntityToModel(MovieEntity movieEntity) {
		Movie movie = new Movie();
		movie.setId(movieEntity.getId());
		movie.setReleaseDate(movieEntity.getReleaseDate());
		movie.setName(movieEntity.getName());
		movie.setRating(movieEntity.getRating());
		movie.setLength(movieEntity.getLength());
		movie.setCasting(movieEntity.getCasting());
		movie.setDirector(movieEntity.getDirector());
		movie.setDescription(movieEntity.getDescription());
		movie.setWriter(movieEntity.getWriter());
		movie.setGenre(mapGenreEntityListToMovie(movieEntity.getGenres()));
		return movie;
	}
	
	private String mapGenreEntityListToMovie(List<GenreEntity> genreEntities) {
		if (genreEntities.size() <= 0) {
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (GenreEntity ge : genreEntities) {
			stringBuilder.append(ge.getType());
			if (genreEntities.indexOf(ge) != genreEntities.size() - 1) {
				stringBuilder.append(", ");
			}
		}
		return stringBuilder.toString();
	}
	
	public List<Movie> search(String criteria, String value) {
		MovieRepository movieRepository = RepositoryFactory.getMovieRepository();
		List<MovieEntity> movieEntities = movieRepository.search(criteria, value);

		return transformMovieEntityToMovie(movieEntities);
	}
	
	private List<Movie> transformMovieEntityToMovie(List<MovieEntity> movieEntities) {
		List<Movie> movies = new ArrayList<Movie>();
		for (MovieEntity movieEntity : movieEntities) {
			Movie movie = mapMovieEntityToModel(movieEntity);
			movies.add(movie);
		}
		return movies;
	}
	
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
	public void deleteMovie(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		MovieDao movie = entityManager.find(MovieDao.class, id);
		entityManager.remove(movie);
		entityManager.getTransaction().commit();
		entityManager.close();
		emf.close();		
	}
	
	//UPDATE WITH JPA
	public void updateMovie(Movie movie, int[] movieGenreIds, int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fiimdb");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		MovieDao movieDao = new MovieDao();
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
		movie.setDescription(movieDao.getDescription());
		movie.setWriter(movieDao.getWriter());		
		return movie;
	}
}