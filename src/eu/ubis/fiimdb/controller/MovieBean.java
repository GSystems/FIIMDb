package eu.ubis.fiimdb.controller;

import java.util.List;

import eu.ubis.fiimdb.model.Movie;
import eu.ubis.fiimdb.service.MovieService;
import eu.ubis.fiimdb.service.ServiceFactory;

public class MovieBean {
	private MovieService movieService = ServiceFactory.getMovieService();

	public List<Movie> getAllMovies() {
		List<Movie> movies = movieService.getMovies();
		return movies;
	}
}