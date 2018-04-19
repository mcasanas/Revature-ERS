package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.ErsUserDAO;
import com.revature.daoimpl.ErsUserDAOImpl;
import com.revature.pojo.ErsUser;

/**
 * Servlet implementation class NewUserServlet
 */
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Views/ERS_NewUser.html");
		rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session
		PrintWriter pw = response.getWriter();
		ErsUser user = new ErsUser();
		ErsUserDAO userdao = new ErsUserDAOImpl();
		//HttpSession session = request.getSession();
		user.setFirstname(request.getParameter("ers_firstname"));
		user.setLastname(request.getParameter("ers_lastname"));
		user.setEmail(request.getParameter("ers_email"));
		user.setUsername(request.getParameter("ers_username"));
		user.setPassword(request.getParameter("ers_password"));
		System.out.println(request.getParameter("ers_firstname"));
		System.out.println(user);
		if(userdao.checkUser(user)) {
			pw.println("unique");
		} else {
			pw.println("not unique");
		}
	}

}
