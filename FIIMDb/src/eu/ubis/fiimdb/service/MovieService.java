package eu.ubis.fiimdb.service;

import java.util.ArrayList;
import java.util.List;

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
		List<Movie> movies = new ArrayList<Movie>();
		for (MovieEntity movieEntity : movieEntities) {
			Movie movie = mapMovieEntityToModel(movieEntity);
			System.out.println("service " + movie.getName());
			movies.add(movie);
		}
		return movies;
	}
	
//	private List<Movie> transformMovieEntityToMovie(List<MovieEntity> movieEntities) {
//		List<Movie> movies = new ArrayList<Movie>();
//		for (MovieEntity movieEntity : movieEntities) {
//			Movie movie = mapMovieEntityToModel(movieEntity);
//			
//			System.out.println("new: " + movie.getName());
//			
//			movies.add(movie);
//		}
//		return movies;
//	}
}