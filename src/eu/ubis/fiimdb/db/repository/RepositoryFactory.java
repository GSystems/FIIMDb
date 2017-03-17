package eu.ubis.fiimdb.db.repository;

public class RepositoryFactory {
	private static MovieRepository movieRepository;

	public static MovieRepository getMovieRepository() {
		if (movieRepository == null) {
			movieRepository = new MovieRepository();
		}

		return movieRepository;
	}
}
