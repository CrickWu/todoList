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
 * Servlet implementation class DealItem
 */
public class ModifyItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemService itemService;

	@Override
	public void init() throws ServletException {
		itemService = new ItemService();
	}   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyItem() {
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
		int error = -3;
		HttpSession session = request.getSession();
		
		String idString = request.getParameter("id");
		//TODO change to viewTodo.jsp?? any need to set this?
		session.setAttribute("url", "viewTodo.jsp");
		int id;
		ItemEntry newItemEntry = new ItemEntry();
		//why
		try {
		id = Integer.parseInt(idString);
		
		String titleString = request.getParameter("title");
		String contentString = request.getParameter("content");
		String rank = request.getParameter("rank");
		String username = (String) session.getAttribute("username"); 
		int rankInt = Integer.parseInt(rank);
		String date = request.getParameter("date");
		newItemEntry= new ItemEntry(id, titleString, contentString, date, rankInt, username);
		
		} catch (NumberFormatException e) {
			e.printStackTrace(System.out);
			error = -2;
		}
		
		if (error == -3)
			error = itemService.modify(newItemEntry);
		
		
		session.setAttribute("error", (Integer)error);
		//for test
		if (error > 0) {
			session.setAttribute("info", "Todo list modifieded successfully!");
		} else {
			session.setAttribute("info", "Todo list modifieded unsuccessfully!");
		}
		response.sendRedirect("viewTodo.jsp");
	}

}
