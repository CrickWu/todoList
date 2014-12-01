package com.demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DealLogout
 */
public class DealLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DealLogout() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String usernameString = (String) session.getAttribute("username");
		String passwordString = (String) session.getAttribute("password");

		session.setAttribute("info", "You have logged out!");
		if (usernameString != null) {
			Cookie cookie = new Cookie(usernameString, passwordString);
			cookie.setMaxAge(0);
			cookie.setDomain("/");
			response.addCookie(cookie);
		}

		session.removeAttribute("password");
		session.removeAttribute("username");
		// this needs to be replaced

		response.sendRedirect("index.jsp");
	}

}
