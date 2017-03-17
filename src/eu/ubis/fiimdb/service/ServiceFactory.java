package eu.ubis.fiimdb.service;

public final class ServiceFactory {
	private static MovieService movieService;
	
	public static MovieService getMovieService() {
		if(movieService == null) {
			movieService = new MovieService();
		}
		
		return movieService;
	}
}
