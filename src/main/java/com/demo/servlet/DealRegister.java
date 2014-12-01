package com.demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.dao.UserEntry;
import com.demo.service.UserService;

/**
 * Servlet implementation class DealRegister
 */
public class DealRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		userService = new UserService();
	}

	/**
	 * @throws ServletException
	 * @see HttpServlet#HttpServlet()
	 */
	public DealRegister() throws ServletException {
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
		int error = -2;
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		UserEntry newUser = new UserEntry(user, password);
		System.out.println(user + " " + password);
		System.out.println(newUser);
		error = userService.isInTable(newUser);

		HttpSession session = request.getSession();
		session.setAttribute("url", "viewTodo.jsp");
		if (error == -1)
			userService.create(newUser);
//		session.setAttribute("error", error);
//		response.sendRedirect("register.jsp");
		 if (error == -1)
		 session.setAttribute("info", "Registered successfully!");
		 else if (error == 1)
		 session.setAttribute("info", "Registered unsuccessfully!");
		 else {
			session.setAttribute("info", "exception ocurrs.");
		}
		 // session.setAttribute("error",
		 // ((Integer)error).toString());
		 response.sendRedirect("index.jsp");
		return;
	}
}
