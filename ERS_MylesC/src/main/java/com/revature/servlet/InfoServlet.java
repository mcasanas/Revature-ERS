package com.revature.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

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
		if(session.getAttribute("ticketId") != null) { 
			int i = Integer.parseInt(session.getAttribute("ticketId").toString());
			ticket.setReim_id(i);
			ticket = ticketDao.getTicket(ticket);
	
			ticketString = om.writeValueAsString(ticket);
			pw.write(ticketString);
		} else {
			
		}
	}

	//Update ticket from admin
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ErsTicketDAO ticketDao =  new ErsTicketDAOImpl();
		ErsTicket ticket = new ErsTicket();
		int statusId = Integer.parseInt(request.getParameter("statusId"));
		if(session != null) { 
			ticket.setReim_id(Integer.parseInt(session.getAttribute("ticketId").toString()));
			ticket.setReim_status_id(statusId);
			ticketDao.resolveTicket(ticket);
			response.sendRedirect("/ERS_MylesC/Views/ERS_Table.html");
		} else{
			response.sendRedirect("login");
		}
	}

}
