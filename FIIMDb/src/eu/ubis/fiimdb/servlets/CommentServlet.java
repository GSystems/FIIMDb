package eu.ubis.fiimdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.ubis.fiimdb.controller.CommentBean;
import eu.ubis.fiimdb.model.Comment;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch(action) {
			case("saveComment"):
				saveComment(request,response);
				break;
		}
	}
	
	private void saveComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CommentBean commentBean = new CommentBean();
		Comment comment = new Comment();
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		String username = request.getRemoteUser();
		comment.setComment(request.getParameter("comment"));
		
		commentBean.saveComment(comment, username, movieId);
		response.sendRedirect("movie-details.jsp");
	}
}