package eu.ubis.fiimdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.ubis.fiimdb.controller.MovieBean;

public class StartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public StartServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MovieBean bean = new MovieBean();
		bean.getAllMovies();
		
		// set the bean as attribute to the request in order to list the movies in the jsp 
		// make sure that it is the name from movies.jsp (see tag <jsp:useBean>)
		request.setAttribute("movieBean", bean);
		
		// set which radio button will be checked ? clicked
		request.setAttribute("searchType", "");

		//redirect to movie.jsp
//		getServletContext().getRequestDispatcher("/movies.jsp").forward(request, response);
		request.getRequestDispatcher("/movies.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {}
}