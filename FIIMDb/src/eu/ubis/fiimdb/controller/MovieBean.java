package eu.ubis.fiimdb.controller;

import java.util.ArrayList;
import java.util.List;

import eu.ubis.fiimdb.model.Genre;
import eu.ubis.fiimdb.model.Movie;
import eu.ubis.fiimdb.service.CommentService;
import eu.ubis.fiimdb.service.GenreService;
import eu.ubis.fiimdb.service.MovieService;
import eu.ubis.fiimdb.service.ServiceFactory;

public class MovieBean {
	private MovieService movieService = ServiceFactory.getMovieService();
	private GenreService genreService = ServiceFactory.getGenreService();
	private CommentService commentService = ServiceFactory.getCommentService();
	
	private static List<Movie> movies = new ArrayList<Movie>();
	
	public List<Movie> getMovies() {
		return movies;
	}
	
	public void getAllMovies(int pageNumber, int pageSize) {
		
		movies = movieService.getMovies(pageNumber, pageSize);
	}
	
	public void search(String criteria, String value) {
		movies = movieService.search(criteria, value);
	}
		
	public void insertMovie(Movie movie, int[] movieGenreIds) {
		movieService.insertMovie(movie, movieGenreIds);
	}
	
	public List<Genre> getGenres() {
		return genreService.getGenres();
	}
	
	public void movieDetails(int id) {
		movies.clear();
		movies.add(movieService.getMovieById(id));
	}
	
	public void deleteMovie(int movieId) {
		commentService.deleteAllCommentsOfAMovie(movieId);
		movieService.deleteMovie(movieId);
		movies = movieService.getMoviesNoPagination();
	}
	
	public void updateMovie(Movie movie, int[] movieGenreIds, int id) {
		movieService.updateMovie(movie, movieGenreIds, id);
	}
}