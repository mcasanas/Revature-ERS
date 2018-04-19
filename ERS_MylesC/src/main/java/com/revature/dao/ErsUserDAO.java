package com.revature.dao;
import com.revature.pojo.ErsUser;


public interface ErsUserDAO {
	public boolean checkUser(ErsUser user); //for uniqueness/ true 
	public boolean addAccount(ErsUser user);
	public boolean loginUser(ErsUser user);
	public ErsUser getUserbyId(int myId);
	public ErsUser getUserbyUsername(String username);

	public boolean deleteAccount(ErsUser user);
}
