package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.ErsUser;

/**
 * Servlet implementation class UserInfoServlet
 */
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		//System.out.println(session.getAttribute("userId"));
		if(session.getAttribute("userId") != null || session.getAttribute("userId") != "")
		{
			response.setContentType("application/json");
			//System.out.println("name");
			//System.out.println(session.getAttribute("username"));
			ObjectMapper om = new ObjectMapper();
			ErsUser user = new ErsUser();
			user.setUsername((String)session.getAttribute("username"));
			user.setUser_id((Integer)session.getAttribute("userId"));
			user.setRole_id((Integer)session.getAttribute("roleId"));
			user.setUser_role((String)session.getAttribute("role"));
			//System.out.println(user);
			String out = om.writeValueAsString(user);
			response.getWriter().write(out);
			//response.getWriter().write("{\"username\":\""+session.getAttribute("username")+"\"}");
			//response.getWriter().write("{\"roleId\":\""+session.getAttribute("roleId")+"\"}");
		} else {
			response.setContentType("application/json");
			response.getWriter().write("{\"message\":null}");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
