package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.ErsUserDAO;
import com.revature.daoimpl.ErsUserDAOImpl;
import com.revature.pojo.ErsUser;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
    	super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		//session.invalidate();
		System.out.println("get");
		HttpSession session = request.getSession(false);
		if(session != null) {
			//System.out.println("invalidate");
			//session.invalidate();
		} else {
			//System.out.println("continue");
			//session = request.getSession(true);
		}
		request.getRequestDispatcher("Views/ERS_Login.html").include(request, response);;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			// session.invalidate();
			session = request.getSession(true);
			
		} else {
			session = request.getSession(true);
		}
		session.setAttribute("message", null);
		//System.out.println("post");
		ErsUser user = new ErsUser();
		ErsUserDAO dao = new ErsUserDAOImpl();
		user.setUsername(request.getParameter("ers_username"));
		user.setPassword(request.getParameter("ers_password"));
		if(dao.loginUser(user)) {
			//correct
			//session.setAttribute("message", "correct");
			session.setAttribute("message", "");
			session.setAttribute("username", request.getParameter("ers_username"));
			user = dao.getUserbyUsername(request.getParameter("ers_username"));
			//System.out.println(user);
			session.setAttribute("userId", user.getUser_id());
			session.setAttribute("roleId", user.getRole_id());
			session.setAttribute("role", user.getUser_role());
			response.sendRedirect("/ERS_MylesC/Views/ERS_Options.html");
		} else {
			session.setAttribute("message", "Invalid Login");
			//System.out.println("redirect back to login");
			response.sendRedirect("login");
		}
		
	}

}
