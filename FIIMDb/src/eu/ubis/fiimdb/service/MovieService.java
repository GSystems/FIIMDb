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
}