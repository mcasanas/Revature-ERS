package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojo.*;

public interface ErsTicketDAO {
	public ErsTicket getTicket(ErsTicket ticket); //by ticket id
	public ArrayList<ErsTicket> getTicketsByUserID(ErsTicket ticket);//user history
	public ArrayList<ErsTicket> getTicketsForAdmin(); //all tickets
	
	public boolean addTicketFromUser(ErsTicket ticket); 
	public boolean resolveTicket(ErsTicket ticket); 

	public boolean deleteTicket(ErsTicket ticket);//For JUNIT testing
}
