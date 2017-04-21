package eu.ubis.fiimdb.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.ubis.fiimdb.controller.MovieBean;
import eu.ubis.fiimdb.model.Movie;

/**
 * Servlet implementation class MovieServlet
 */
@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private int[] movieGenreIds;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		switch (action) {
			case "details":
				movieDetails(request, response);
				break;
			case "delete":
				movieDelete(request, response);
				break;
			case "insert":
				movieInsert(request, response);
				break;
			case "update":
				movieUpdate(request, response);
				break;
			case "saveUpdates":
				saveUpdates(request, response);
				break;
		}
	}
	
	private void movieDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("movie-details.jsp");
		int id = Integer.parseInt(request.getParameter("detailsButton"));
		MovieBean movie = new MovieBean();
		movie.movieDetails(id);
	}
	
	private void movieDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("deleteMovie"));
		MovieBean movieBean = new MovieBean();
		movieBean.deleteMovie(id);
		response.sendRedirect("movies.jsp");
	}
	
	private void movieInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Movie movie = getMovieInfo(request, response);
		
   		MovieBean movieB = new MovieBean();
		movieB.insertMovie(movie, movieGenreIds);
   		response.sendRedirect("movies.jsp");
	}
	
	private void movieUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MovieBean movieBean = new MovieBean();
		
		int id = Integer.parseInt(request.getParameter("updateMovie"));
		movieBean.movieDetails(id);
		response.sendRedirect("movie-update.jsp");
	}
	
	private void saveUpdates(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MovieBean movieBean = new MovieBean();
		int id = Integer.parseInt(request.getParameter("movieId"));
		Movie movie = getMovieInfo(request, response);	
		movieBean.updateMovie(movie, movieGenreIds, id);
		response.sendRedirect("movies.jsp");
	}

	private Movie getMovieInfo(HttpServletRequest request, HttpServletResponse response) {
		Movie movie = new Movie();
		movie.setName(request.getParameter("name"));
		movie.setRating(Double.parseDouble(request.getParameter("rating")));
		movie.setLength(Integer.parseInt(request.getParameter("length")));
		movie.setCasting(request.getParameter("casting"));
		
		String myDate = request.getParameter("releaseDate");
		SimpleDateFormat parseDate = new java.text.SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		
		try {
			date = (Date) parseDate.parse(myDate);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
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
		return movie;
	}
}
