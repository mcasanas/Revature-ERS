package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ErsTicketDAO;
import com.revature.daoimpl.ErsTicketDAOImpl;
import com.revature.pojo.ErsTicket;


public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public InfoServlet() {
        super();
    }
    //Return info on ticket
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ErsTicketDAO ticketDao =  new ErsTicketDAOImpl();
		ErsTicket ticket = new ErsTicket();
		ObjectMapper om = new ObjectMapper();
		String ticketString = "";
		PrintWriter pw = response.getWriter();
		if(session != null && ((Integer) session.getAttribute("ticketId")) == 1) { 
			ticket.setReim_id((Integer) session.getAttribute("ticketId"));
			ticket = ticketDao.getTicket(ticket);
			ticketString = om.writeValueAsString(ticket);
			pw.write(ticketString);
		} else{
			response.sendRedirect("login");
		}
	}

	//Update ticket from admin
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ErsTicketDAO ticketDao =  new ErsTicketDAOImpl();
		ErsTicket ticket = new ErsTicket();
		if(session != null && ((Integer) session.getAttribute("ticketId")) == 1) { 
			ticket.setReim_id((Integer) session.getAttribute("ticketId"));
			ticket.setReim_status_id((Integer) session.getAttribute("statusId"));
			ticketDao.resolveTicket(ticket);
			response.sendRedirect("table");
		} else{
			response.sendRedirect("login");
		}
	}

}
