package eu.ubis.fiimdb.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import eu.ubis.fiimdb.controller.MovieBean;
import eu.ubis.fiimdb.model.Movie;

public class MovieBeanTest extends MovieBean {

	@Test
	public void testSearch() {
		String criteria = "genre";
		String value = "science-fiction";
		MovieBean movieBean = new MovieBean();
		movieBean.search(criteria, value);
		List<Movie> movies = movieBean.getMovies();
		assertNotNull(movies);
		
		int dbCounter = 2;
		int movieCounter = 0;
		
		for (int i = 0; i < movies.size(); i++) {
			movieCounter++;
		}
		assertEquals(dbCounter, movieCounter);
	}

}
