package com.revature.daoimpl;

import java.sql.*;

import com.revature.dao.ErsUserDAO;
import com.revature.pojo.ErsUser;
import com.revature.util.ConnectionFactory;

public class ErsUserDAOImpl implements ErsUserDAO {

	@Override
	public boolean checkUser(ErsUser user) { //check username
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT ERS_USERNAME, USER_EMAIL FROM ERS_USERS WHERE ERS_USERNAME = ? OR USER_EMAIL = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ResultSet rs = ps.executeQuery();
			int count = 0;
			while(rs.next()) {
				count++;
			}
			if(count >= 1) {
				return false;//Username exists
			} else {
				return true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addAccount(ErsUser user) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "{CALL ADD_USER(?,?,?,?,?,?)}";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, user.getUsername());
			cs.setString(2, user.getPassword());
			cs.setString(3, user.getFirstname());
			cs.setString(4, user.getLastname());
			cs.setString(5, user.getEmail());
			cs.setInt(6, user.getRole_id());
			int rows = cs.executeUpdate();
			if(rows != 1) {
				return false;
			} else {
				return true;
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean loginUser(ErsUser user) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ? AND ERS_PASSWORD = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			int count = 0;
			while(rs.next()) {
				count++;
			}
			if(count != 1) {
				return false;
			} else {
				return true;
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}

	@Override
	public ErsUser getUserbyId(int myId) {
		ErsUser temp = new ErsUser();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERS_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,myId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				temp.setUser_id(rs.getInt("ERS_USERS_ID"));
				temp.setUsername(rs.getString("ERS_USERNAME"));
				temp.setFirstname(rs.getString("USER_FIRST_NAME"));
				temp.setLastname(rs.getString("USER_LAST_NAME"));
				temp.setEmail(rs.getString("USER_EMAIL"));
				temp.setRole_id(rs.getInt("USER_ROLE_ID"));
			}
			switch (temp.getRole_id()){
				case 1: temp.setUser_role("Manager"); break;
				case 2: temp.setUser_role("Junior"); break;
				case 3: temp.setUser_role("Middle"); break;
				case 4: temp.setUser_role("Senior"); break;
				case 5: temp.setUser_role("Lead"); break;
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return temp;
	}
	@Override
	public ErsUser getUserbyUsername(String username) {
		ErsUser temp = new ErsUser();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				temp.setUser_id(rs.getInt("ERS_USERS_ID"));
				temp.setUsername(rs.getString("ERS_USERNAME"));
				temp.setFirstname(rs.getString("USER_FIRST_NAME"));
				temp.setLastname(rs.getString("USER_LAST_NAME"));
				temp.setEmail(rs.getString("USER_EMAIL"));
				temp.setRole_id(rs.getInt("USER_ROLE_ID"));
			}
			switch (temp.getRole_id()){
				case 1: temp.setUser_role("Manager"); break;
				case 2: temp.setUser_role("Junior"); break;
				case 3: temp.setUser_role("Middle"); break;
				case 4: temp.setUser_role("Senior"); break;
				case 5: temp.setUser_role("Lead"); break;
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return temp;
	}

	@Override
	public boolean deleteAccount(ErsUser user) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "DELETE ERS_USERS WHERE ERS_USERNAME = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			int rows = ps.executeUpdate();
			if(rows != 1) {
				return false;
			} else {
				return true;
			}
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}

	

}
