package eu.ubis.fiimdb.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.ubis.fiimdb.controller.MovieBean;
import eu.ubis.fiimdb.model.Movie;

@WebServlet("/MovieInsert")
public class MovieInsertServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Movie movie = new Movie();
		int[] movieGenreIds;
		
		movie.setName(request.getParameter("name"));
		movie.setRating(Double.parseDouble(request.getParameter("rating")));
		movie.setLength(Integer.parseInt(request.getParameter("length")));
		movie.setCasting(request.getParameter("casting"));
		
		String myDate = request.getParameter("releaseDate");
		SimpleDateFormat parseDate = new java.text.SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		
			try {
				date = (Date) parseDate.parse(myDate);
			} catch (ParseException e) {
				throw new ServletException("invalid");
			}

		movie.setReleaseDate(date);
		movie.setDirector(request.getParameter("director"));
		movie.setDescription(request.getParameter("description"));
		movie.setWriter(request.getParameter("writer"));
		
		String[] genreIdStrings = request.getParameterValues("genres");
		movieGenreIds = new int[genreIdStrings.length];
		for(int i = 0; i < genreIdStrings.length; i++) {
			movieGenreIds[i] = Integer.parseInt(genreIdStrings[i].trim());
		}
		
   		MovieBean movieB = new MovieBean();
		movieB.insertMovie(movie, movieGenreIds);
   		response.sendRedirect("movies.jsp");
	}
}