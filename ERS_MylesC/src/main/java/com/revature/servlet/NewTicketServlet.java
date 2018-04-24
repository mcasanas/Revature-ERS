package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.ErsTicketDAO;
import com.revature.daoimpl.ErsTicketDAOImpl;
import com.revature.pojo.ErsTicket;


public class NewTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewTicketServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ErsTicket ticket = new ErsTicket();
		ErsTicketDAO ticketDao = new ErsTicketDAOImpl();
		ticket.setReim_amount(Double.parseDouble(request.getParameter("ers_amount")));
		ticket.setReim_descript(request.getParameter("ers_descript"));
		ticket.setReim_author_id((Integer) session.getAttribute("userId"));
		ticket.setReim_type_id(Integer.parseInt(request.getParameter("ers_type_id")));
		ticketDao.addTicketFromUser(ticket);
		session.setAttribute("message", null);
		session.setAttribute("message", "Ticket has been added");
		response.sendRedirect("/ERS_MylesC/Views/ERS_Options.html");
	}

}
