package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.dao.UserEntry;
import com.demo.service.UserService;

/**
 * Servlet implementation class DealLogin
 */
public class DealLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DealLogin() {
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
	 * @throws IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private void resend(HttpServletRequest request,
			HttpServletResponse response, String username, String password)
			throws IOException {
		//TODO b shows whether to cookie (not seemly necessary) for only develop in post
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		session.setAttribute("info", "User name and password are correct!");
		if (request.getAttribute("remember") != null) {
			Cookie cookie = new Cookie(username, password);
			cookie.setMaxAge(7 * 24 * 60 * 60);
			cookie.setDomain("/");
			response.addCookie(cookie);
		}
		// this needs to be replaced
		response.sendRedirect("viewTodo.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie[] cookies = request.getCookies();
		HttpSession session = request.getSession();

		UserService userService = new UserService();
		// list return a set??
		// ArrayList<UserEntry> arrayList = userService.getList();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (userService.isCorrect(new UserEntry(cookie.getName(),
						cookie.getValue()))) {
					resend(request, response, cookie.getName(),
							cookie.getValue());
					return;
				}
			}
		}

		String usernameString = (String) request.getParameter("username");
		String passwordString = (String) request.getParameter("password");
		if (userService
				.isCorrect(new UserEntry(usernameString, passwordString))) {
			resend(request, response, usernameString, passwordString);
			return;
		}

		usernameString = (String) session.getAttribute("username");
		passwordString = (String) session.getAttribute("password");
		if (userService
				.isCorrect(new UserEntry(usernameString, passwordString))) {
			resend(request, response, usernameString, passwordString);
			return;
		}

		session.setAttribute("info",
				"User name and password are not compatible!");
		response.sendRedirect("index.jsp");
		return;
	}

}
