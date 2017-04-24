package eu.ubis.fiimdb.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.ubis.fiimdb.captchas.CaptchasDotNet;
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
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action.equals(null))
			response.sendRedirect("movies.jsp");
		
		switch (action) {
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
				try {
					resetPassword(request, response);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				break;
			case "insertNewUser":
				insertNewUser(request, response);
				break;
			case "captcha":
				captcha(request, response);
				break;
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        response.sendRedirect("movies.jsp?pageNumber=1");
	}
	
	private void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CaptchasDotNet captchas = new CaptchasDotNet(
				  request.getSession(true),     // Ensure session
				  "demo",                       // client
				  "secret"                      // secret
				  );
 		String captchaMessage = request.getParameter("captchaMessage");
 		
 		boolean correctMessage;
 		
 		switch (captchas.check(captchaMessage)) {
 		  case 's':
 			  correctMessage = false;
 			  break;
 		  case 'm':
 			 correctMessage = false;
 			  break;
 		  case 'w':
 			 correctMessage = false;
 			  break;
 		  default:
 			  correctMessage = true;
 		    break;
 		}
 		
 		if(correctMessage == true)
 			response.sendRedirect("login.jsp?correct=true");
 		else response.sendRedirect("home.jsp");
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("home.jsp?correct=false");
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
		response.sendRedirect("movies.jsp?pageNumber=1");
	}
	
	private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
		UserBean userBean = new UserBean();
		String oldPassword = request.getParameter("oldpassword");
		String newPassword = request.getParameter("newpassword");
		String confirmPassword = request.getParameter("confirmpassword");
		if(newPassword.equals(confirmPassword))
			userBean.resetPassword(oldPassword, newPassword);
		response.sendRedirect("user-profile.jsp");
	}
	
	public void insertNewUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String error = null;
		UserBean userBean = new UserBean();
		User user = getNewUserInfo(request, response);
		try {
			userBean.insertNewUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
			error = e.getMessage();
			System.out.println(error);
		}
		if(error == null)
			response.sendRedirect("movies.jsp?pageNumber=1");
		else if ("this username already exists".equals(error))
			response.sendRedirect("register.jsp?errorId=1");
		else response.sendRedirect("register.jsp?errorId=2");
	}
	
	private User getUserPersonalInfo(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setFirstname(request.getParameter("firstname"));
		user.setLastname(request.getParameter("lastname"));
		user.setEmail(request.getParameter("email"));
		return user;
	}
	
	private User getNewUserInfo(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		user.setUsername(request.getParameter("r_username"));
		user.setPassword(request.getParameter("r_password"));
		user.setFirstname(request.getParameter("r_firstname"));
		user.setLastname(request.getParameter("r_lastname"));
		user.setEmail(request.getParameter("r_email"));
		return user;
	}
}