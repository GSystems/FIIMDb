package eu.ubis.fiimdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.ubis.fiimdb.controller.IpBean;
import eu.ubis.fiimdb.model.Ip;

/**
 * Servlet implementation class IpServlet
 */
@WebServlet("/IpServlet")
public class IpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IpServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ip = request.getParameter("ip");
		String username = request.getParameter("username");
		IpBean ipBean = new IpBean();
		Ip ipC = new Ip();
		ipC.setIp(ip);
		ipC.setUser(username);
		ipBean.saveIp(ipC, username);
		response.sendRedirect("home.jsp");
	}
}
