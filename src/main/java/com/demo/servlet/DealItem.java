package com.demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.dao.ItemEntry;
import com.demo.service.ItemService;

/**
 * Servlet implementation class DealItem
 */
public class DealItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemService itemService;

	@Override
	public void init() throws ServletException {
		itemService = new ItemService();
	}   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DealItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int error = -2;
		HttpSession session = request.getSession();
		String titleString = request.getParameter("title");
		String contentString = request.getParameter("content");
		String rank = request.getParameter("rank");
		String username = (String) session.getAttribute("username"); 
		int rankInt = -1;
		try { 
			rankInt = Integer.parseInt(rank);
		} catch (NumberFormatException e) {
			e.printStackTrace(System.out);
		}
		String date = request.getParameter("date");
		ItemEntry newItemEntry = new ItemEntry(titleString, contentString, date, rankInt, username);
		error = itemService.create(newItemEntry);
		
		session.setAttribute("error", (Integer)error);
		//for test
		session.setAttribute("url", "viewTodo.jsp");
		if (error > 0) {
			session.setAttribute("info", "Todo list added successfully!");
		} else {
			session.setAttribute("info", "Todo list added unsuccessfully!");
		}
		response.sendRedirect("viewTodo.jsp");
	}

}
