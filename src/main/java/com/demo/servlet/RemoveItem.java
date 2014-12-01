package com.demo.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.dao.ItemEntry;
import com.demo.service.ItemService;

/**
 * Servlet implementation class RemoveItem
 */
public class RemoveItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemService itemService;

	@Override
	public void init() throws ServletException {
		itemService = new ItemService();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveItem() {
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
		int error = -2;
		HttpSession session = request.getSession();
		String idString = request.getParameter("id");
		//TODO change to viewTodo.jsp?? any need to set this?
		session.setAttribute("url", "viewTodo.jsp");
		int id;
		//why
		try {
		id = Integer.parseInt(idString);
		error = itemService.remove(id);
		} catch (NumberFormatException e) {
			e.printStackTrace(System.out);
		}
		
		session.setAttribute("error", (Integer)error);
		//for test
		if (error > 0) {
			session.setAttribute("info", "Todo list removed successfully!");
		} else {
			session.setAttribute("info", "Todo list removed unsuccessfully!");
		}
		//TODO change too viewTodo.jsp
		response.sendRedirect("viewTodo.jsp");
	}

}
