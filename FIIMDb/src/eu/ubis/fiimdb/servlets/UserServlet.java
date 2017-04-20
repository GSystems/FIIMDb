package eu.ubis.fiimdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.ubis.fiimdb.controller.UserBean;
import eu.ubis.fiimdb.model.User;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action.equals(null))
			response.sendRedirect("movies.jsp");
		
		switch(action) {
			case "logout":
				logout(request,response);
				break;
			case "login":
				login(request,response);
				break;
			case "register":
				register(request,response);
				break;
			case "userProfile":
				userProfile(request, response);
				break;
			case "saveProfile":
				saveProfile(request, response);
				break;
			case "resetPassword":
				resetPassword(request, response);
				break;
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        //no encoding because we have invalidated the session
        response.sendRedirect("movies.jsp");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("home.jsp");
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("register.jsp");
	}
	
	private void userProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("user-profile.jsp");
		UserBean userBean = new UserBean();
		String username = request.getRemoteUser();
		userBean.getUserByUsername(username);
	}
	
	private void saveProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserBean userBean = new UserBean();
		User user = getUserPersonalInfo(request,response);
		String username = request.getRemoteUser();
		userBean.updateUserInfo(user, username);
		response.sendRedirect("movies.jsp");
	}
	
	private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserBean userBean = new UserBean();
		String oldPassword = request.getParameter("oldpassword");
		String newPassword = request.getParameter("newpassword");
		String confirmPassword = request.getParameter("confirmpassword");
		if(newPassword.equals(confirmPassword))
			userBean.resetPassword(oldPassword, newPassword);
		response.sendRedirect("user-profile.jsp");
	}
	
	private User getUserPersonalInfo(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setFirstname(request.getParameter("firstname"));
		user.setLastname(request.getParameter("lastname"));
		user.setEmail(request.getParameter("email"));
		return user;
	}
}