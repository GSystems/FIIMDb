package eu.ubis.fiimdb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.ubis.fiimdb.controller.MovieBean;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		MovieBean movie = new MovieBean();
		String searchedValue = request.getParameter("searchedValue");
		String searchType 	 = request.getParameter("searchType");
		
		switch(searchType) {
			case "name":
				movie.search("name", searchedValue);
				break;
			case "genre":
				movie.search("genre", searchedValue);
				break;
			case "year":
				movie.search("year", searchedValue);
				break;
		}
		response.sendRedirect("movies.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
	}
}