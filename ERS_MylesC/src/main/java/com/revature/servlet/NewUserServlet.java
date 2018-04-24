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
		//PrintWriter pw = response.getWriter();
		ErsUser user = new ErsUser();
		ErsUserDAO userdao = new ErsUserDAOImpl();
		String message = new String();
		request.setAttribute("message", "hello");
		String destination = "login";
		//HttpSession session = request.getSession();
		user.setFirstname(request.getParameter("ers_firstname"));
		user.setLastname(request.getParameter("ers_lastname"));
		user.setEmail(request.getParameter("ers_email"));
		user.setUsername(request.getParameter("ers_username"));
		user.setPassword(request.getParameter("ers_password"));
		user.setRole_id(Integer.parseInt(request.getParameter("ers_role_id")));
		//request.getSession().removeAttribute(message);
		//System.out.println("here");
		//request.getRequestDispatcher("/ERS_MylesC/newUser").forward(request, response);;
		if(!user.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			message = "Invalid Email Format";
			System.out.println("wrong email");
			destination = "newUser";
		}else if(!user.getPassword().matches(request.getParameter("confirm_password"))) {
			System.out.println("needs match pass");
			message = "Passwords Do Not Match";
			destination = "newUser";
		}else if(!userdao.checkUser(user)) {
			message = "Username or Email Already Exists";
			//System.out.println("exists");
			destination = "newUser";
		} else {
			//System.out.println("mynewuser");
			userdao.addAccount(user);
			message = "Created New User";
			destination = "login";
		}
		HttpSession session = request.getSession(false);
		session.setAttribute("message", message);
		response.sendRedirect(destination);
	}

}
