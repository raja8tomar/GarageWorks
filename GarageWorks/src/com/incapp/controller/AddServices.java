package com.incapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.incapp.dao.GarageDao;
import com.incapp.dao.UserDao;

/**
 * Servlet implementation class AddEnquires
 */
@WebServlet("/AddServices")
public class AddServices extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
				String email=request.getParameter("email");
				String services=request.getParameter("services");
				
			 GarageDao d=new GarageDao();
			 String result= d.setService(services, email);
			 d.disconnect();
			 HttpSession session=request.getSession();
			 session.setAttribute("msg",result);
			 response.sendRedirect("garageOwnerHome.jsp");
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rs=request.getRequestDispatcher("ExpPage.jsp");
			rs.forward(request, response);
			
		}
		
	}

}
