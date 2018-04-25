package com.revature.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601Utils;
import com.revature.dao.ErsTicketDAO;
import com.revature.daoimpl.ErsTicketDAOImpl;
import com.revature.pojo.ErsTicket;


public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TableServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Supply information on table
		HttpSession session = request.getSession(false);
		ErsTicketDAO ticketDao =  new ErsTicketDAOImpl();
		//ArrayList<ErsTicket> ticketList = new ArrayList<ErsTicket>();
		ObjectMapper om = new ObjectMapper();
		String tableString = "";
		PrintWriter pw = response.getWriter();
		if(session != null && ((Integer) session.getAttribute("roleId")) == 1) { //admin
			ArrayList<ErsTicket> ticketList = new ArrayList<ErsTicket>();
			ticketList = ticketDao.getTicketsForAdmin();
			tableString = om.writeValueAsString(ticketList);
			pw.write(tableString);
		} else if(session != null && ((Integer) session.getAttribute("roleId")) >1 && (Integer)session.getAttribute("roleId") <6) {
			int userId = (Integer) session.getAttribute("userId");
			ErsTicket temp = new ErsTicket();
			ArrayList<ErsTicket> ticketList = new ArrayList<ErsTicket>();
			temp.setReim_author_id(userId);
			ticketList = ticketDao.getTicketsByUserID(temp);
			tableString = om.writeValueAsString(ticketList);
			pw.write(tableString);
		} else{
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get info on Ticket
		HttpSession session = request.getSession(false);
		InputStream myIS = request.getInputStream();
		String textInput = null;
	    try (Scanner scanner = new Scanner(myIS, StandardCharsets.UTF_8.name())) {
	        textInput = scanner.useDelimiter("\\A").next();
	    }
		//System.out.println(textInput);
		session.setAttribute("ticketId", textInput);
		//System.out.println(session.getAttribute("ticketId"));
	}

}
