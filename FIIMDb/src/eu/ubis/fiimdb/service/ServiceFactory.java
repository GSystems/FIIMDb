package eu.ubis.fiimdb.service;

public final class ServiceFactory {
	private static MovieService movieService;
	private static GenreService genreService;
	private static UserService userService;
	
	public static MovieService getMovieService() {
		if(movieService == null) {
			movieService = new MovieService();
		}
		return movieService;
	}

	public static GenreService getGenreService() {
		if(genreService == null) {
			genreService = new GenreService();
		}
		return genreService;
	}
	
	public static UserService getUserService() {
		if(userService == null) {
			userService = new UserService();
		}
		return userService;
	}
}
