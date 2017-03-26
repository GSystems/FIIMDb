package eu.ubis.fiimdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.ubis.fiimdb.controller.MovieBean;
import eu.ubis.fiimdb.model.Movie;

@WebServlet("/MovieInsert")
public class MovieInsertServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Movie movie = new Movie();
		
		movie.setId(Integer.parseInt(request.getParameter("id")));
		movie.setName(request.getParameter("name"));
		movie.setRating(Double.parseDouble(request.getParameter("rating")));
		movie.setLength(Integer.parseInt(request.getParameter("length")));
		movie.setCasting(request.getParameter("casting"));
//		movie.setReleaseDate(Date.parse(request.getParameter("releaseDate")));
		movie.setDescription(request.getParameter("description"));
		movie.setWriter(request.getParameter("writer"));
		
		
		String[] genreIdStrings = request.getParameterValues("genres");
		int[] movieGenreIds = new int[genreIdStrings.length];
		for(int i = 0; i < genreIdStrings.length; i++) {
			movieGenreIds[i] = Integer.parseInt(genreIdStrings[i]);
		}
		
		MovieBean movieB = new MovieBean();
		movieB.insertMovie(movie, movieGenreIds);
		
		response.sendRedirect("movies.jsp");
	}
}
