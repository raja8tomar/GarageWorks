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
@WebServlet("/deleteServices")
public class deleteServices extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
				int id=Integer.parseInt(request.getParameter("id"));
			 GarageDao d=new GarageDao();
			 String result= d.deleteService(id);
			 d.disconnect();
			 HttpSession session=request.getSession();
			 session.setAttribute("msg",result);
			 response.sendRedirect("services.jsp");
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rs=request.getRequestDispatcher("ExpPage.jsp");
			rs.forward(request, response);
			
		}
		
	}

}
