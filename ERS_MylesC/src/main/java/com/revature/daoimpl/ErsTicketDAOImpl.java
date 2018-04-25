package com.revature.daoimpl;
import java.sql.*;
import java.util.ArrayList;

import com.revature.util.*;
import com.revature.dao.ErsTicketDAO;
import com.revature.pojo.ErsTicket;
//import com.revature.pojo.ErsUser;

public class ErsTicketDAOImpl implements ErsTicketDAO {

	public ErsTicket getTicket(ErsTicket ticket) {
		ErsTicket myTicket = new ErsTicket();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIM_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ticket.getReim_id());		
			ResultSet rs = ps.executeQuery();	
			while(rs.next()) {
				myTicket.setReim_id(rs.getInt("reim_id"));
				myTicket.setReim_amount(rs.getDouble("reim_amount"));
				myTicket.setReim_descript(rs.getString("reim_descript"));
				myTicket.setReim_submitted(rs.getString("reim_submitted"));
				myTicket.setReim_resolved(rs.getString("reim_resolved"));
				myTicket.setReim_receipt(rs.getObject("reim_receipt"));
				myTicket.setReim_status_id(rs.getInt("reim_status_id"));
				myTicket.setReim_type_id(rs.getInt("reim_type_id"));
				myTicket.setReim_author_id(rs.getInt("reim_author"));
			}
			sql = "Select REIM_STATUS FROM ERS_REIMBURSEMENT_STATUS WHERE REIM_STATUS_ID = "+myTicket.getReim_status_id();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				myTicket.setReim_status(rs.getString("reim_status"));
			}
			sql = "Select REIM_TYPE FROM ERS_REIMBURSEMENT_TYPE WHERE REIM_TYPE_ID = "+myTicket.getReim_type_id();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				myTicket.setReim_type(rs.getString("reim_type"));
			}
			sql = "Select USER_FIRST_NAME, USER_LAST_NAME FROM ERS_USERS WHERE ERS_USERS_ID = "+myTicket.getReim_author_id();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				myTicket.setReim_author_name((rs.getString("user_first_name") + " " + rs.getString("user_last_name")));
			}
			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return myTicket;
	}

	public ArrayList<ErsTicket> getTicketsByUserID(ErsTicket ticket) {
		ArrayList<ErsTicket> myList = new ArrayList<ErsTicket>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIM_AUTHOR = "+ticket.getReim_author_id();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ErsTicket temp = new ErsTicket();
				temp.setReim_id(rs.getInt("REIM_ID"));
				temp.setReim_amount(rs.getDouble("REIM_AMOUNT"));
				temp.setReim_submitted(rs.getString("REIM_SUBMITTED"));
				temp.setReim_resolved(rs.getString("REIM_RESOLVED"));
				temp.setReim_status_id(rs.getInt("REIM_STATUS_ID"));
				temp.setReim_type_id(rs.getInt("REIM_TYPE_ID"));
				switch(temp.getReim_type_id()) {
				case 1: temp.setReim_type("Lodging"); break;
				case 2: temp.setReim_type("Travel"); break;
				case 3: temp.setReim_type("Food"); break;
				case 4: temp.setReim_type("Other"); break;
				}
				switch(temp.getReim_status_id()) {
				case 1: temp.setReim_status("Pending"); break;
				case 2: temp.setReim_status("Denied"); break;
				case 3: temp.setReim_status("Approved"); break;
				}
				myList.add(temp);
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return myList;
	}
	//includes full name in ErsTicket
	public ArrayList<ErsTicket> getTicketsForAdmin() {
		ArrayList<ErsTicket> myList = new ArrayList<ErsTicket>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT ERS_REIMBURSEMENT.REIM_ID, ERS_REIMBURSEMENT.REIM_AMOUNT,\r\n" + 
					"ERS_REIMBURSEMENT.REIM_SUBMITTED, ERS_REIMBURSEMENT.REIM_RESOLVED, ERS_REIMBURSEMENT.REIM_DESCRIPT, \r\n" + 
					"ERS_USERS.ERS_USERS_ID, ERS_USERS.USER_FIRST_NAME, ERS_USERS.USER_LAST_NAME, ERS_REIMBURSEMENT.REIM_RESOLVER,\r\n" + 
					"ERS_REIMBURSEMENT_STATUS.REIM_STATUS_ID, ERS_REIMBURSEMENT_TYPE.REIM_TYPE_ID,\r\n" + 
					"ERS_REIMBURSEMENT_STATUS.REIM_STATUS, ERS_REIMBURSEMENT_TYPE.REIM_TYPE\r\n" + 
					"FROM ERS_REIMBURSEMENT\r\n" + 
					"INNER JOIN ERS_REIMBURSEMENT_TYPE\r\n" + 
					"ON ERS_REIMBURSEMENT_TYPE.REIM_TYPE_ID = ERS_REIMBURSEMENT.REIM_TYPE_ID\r\n" + 
					"INNER JOIN ERS_REIMBURSEMENT_STATUS\r\n" + 
					"ON ERS_REIMBURSEMENT_STATUS.REIM_STATUS_ID = ERS_REIMBURSEMENT.REIM_STATUS_ID\r\n" + 
					"INNER JOIN ERS_USERS\r\n" + 
					"ON ERS_REIMBURSEMENT.REIM_AUTHOR = ERS_USERS.ERS_USERS_ID";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ErsTicket temp = new ErsTicket();
				temp.setReim_id(rs.getInt("REIM_ID"));
				temp.setReim_amount(rs.getDouble("REIM_AMOUNT"));
				temp.setReim_submitted(rs.getString("REIM_SUBMITTED"));
				temp.setReim_resolved(rs.getString("REIM_RESOLVED"));
				temp.setReim_descript(rs.getString("REIM_DESCRIPT"));
				temp.setReim_author_id(rs.getInt("ERS_USERS_ID"));
				temp.setReim_resolver_id(rs.getInt("REIM_RESOLVER"));
				temp.setReim_status_id(rs.getInt("REIM_STATUS_ID"));
				temp.setReim_type_id(rs.getInt("REIM_TYPE_ID"));
				temp.setReim_status(rs.getString("REIM_STATUS"));
				temp.setReim_type(rs.getString("REIM_TYPE"));
				temp.setReim_author_name(rs.getString("USER_FIRST_NAME")+" "+rs.getString("USER_LAST_NAME"));
				myList.add(temp);
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return myList;
	}


	public boolean addTicketFromUser(ErsTicket ticket) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "{CALL ADD_TICKET(?,?,?,?)}";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setDouble(1, ticket.getReim_amount());
			cs.setString(2, ticket.getReim_descript());
			cs.setInt(3, ticket.getReim_author_id());
			cs.setInt(4,ticket.getReim_type_id());
			cs.execute();
			return true;
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}

	public boolean resolveTicket(ErsTicket ticket) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "UPDATE ERS_REIMBURSEMENT SET REIM_STATUS_ID = ?, REIM_RESOLVED = CURRENT_TIMESTAMP WHERE REIM_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ticket.getReim_status_id());
			ps.setInt(2, ticket.getReim_id());
			int rows = ps.executeUpdate();
			if(rows != 1) {
				return false;
			} else {
				conn.commit();
				return true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	//Used for JUnit testing for after adding a ticket/ adding a user
	public boolean deleteTicket(ErsTicket ticket) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			//conn.setAutoCommit(false);
			System.out.println("Start "+ticket.getReim_id());
			String sql = "DELETE ERS_REIMBURSEMENT WHERE REIM_AUTHOR = " +ticket.getReim_author_id();
			Statement s = conn.createStatement();
			int rows = s.executeUpdate(sql);
			System.out.println(rows);
			if(rows < 1) {
				return false;
			} else {
				conn.commit();
				return true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}

}
