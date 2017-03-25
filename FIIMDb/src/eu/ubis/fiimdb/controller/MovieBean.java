package eu.ubis.fiimdb.controller;

import java.util.List;

import eu.ubis.fiimdb.model.Movie;
import eu.ubis.fiimdb.service.MovieService;
import eu.ubis.fiimdb.service.ServiceFactory;

public class MovieBean {
	private MovieService movieService = ServiceFactory.getMovieService();
	private List<Movie> movies = movieService.getMovies();
	
	public void getAllMovies() {
		movies = movieService.getMovies();
	}
	
	public void search(String criteria, String value) {
		movies = movieService.search(criteria, value);
	}
	
	public List<Movie> getMovies() {
		return movies;
	}	
}