package eu.ubis.fiimdb.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import eu.ubis.fiimdb.db.dao.GenreDao;
import eu.ubis.fiimdb.db.dao.MovieDao;
import eu.ubis.fiimdb.db.entity.GenreEntity;
import eu.ubis.fiimdb.db.entity.MovieEntity;
import eu.ubis.fiimdb.db.repository.MovieRepository;
import eu.ubis.fiimdb.db.repository.RepositoryFactory;
import eu.ubis.fiimdb.model.Movie;

public class MovieService {

	private EntityManager entityManager;
	
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
			
			System.out.println("new: " + movie.getName());
			
			movies.add(movie);
		}
		return movies;
	}
	
	
	//INSERT WITH JPA
	public void insertMovie(Movie movie, int[] movieGenreIds) {
		MovieDao movieDao = mapMovieModelToDao(movie);
		
		List<GenreDao> movieGenres = new ArrayList<>();
		for(int movieGenreId : movieGenreIds) {
			//grija la id-uri... auto_increment
			GenreDao movieGenre = new GenreDao();
			movieGenre.setId(movieGenreId);
			movieGenres.add(movieGenre);
		}
		movieDao.setGenres(movieGenres);
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(movieDao);
		transaction.commit();
	}
	
	private MovieDao mapMovieModelToDao(Movie movie) {
		MovieDao dao = new MovieDao();
		
		dao.setId(movie.getId());
		dao.setReleaseDate(movie.getReleaseDate());
		dao.setName(movie.getName());
		dao.setRating(movie.getRating());
		dao.setLength(movie.getLength());
		dao.setCasting(movie.getCasting());
		dao.setDirector(movie.getDirector());
		dao.setDescription(movie.getDescription());
		dao.setWriter(movie.getWriter());
		
		return dao;
	}
}