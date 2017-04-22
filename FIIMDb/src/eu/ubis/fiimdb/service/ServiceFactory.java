package eu.ubis.fiimdb.service;

public final class ServiceFactory {
	private static MovieService movieService;
	private static GenreService genreService;
	private static UserService userService;
	private static ActorService actorService;
	private static DirectorService directorService;
	
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
	
	public static ActorService getActorService() {
		if(actorService == null) {
			actorService = new ActorService();
		}
		return actorService;
	}
	
	public static DirectorService getDirectorService() {
		if(directorService == null) {
			directorService = new DirectorService();
		}
		return directorService;
	}
}
