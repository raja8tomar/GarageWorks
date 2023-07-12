package com.incapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.incapp.dao.UserDao;

/**
 * Servlet implementation class AddEnquires
 */
@WebServlet("/AddEnquires")
public class AddEnquires extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name=request.getParameter("Name");
			String phone=request.getParameter("Phone");
			String email=request.getParameter("Email");
			 UserDao d=new UserDao();
			 d.setEnquiry(name, phone, email);
			 d.disconnect();
			 HttpSession session=request.getSession();
			 session.setAttribute("msg","Succesful");
			 response.sendRedirect("index.jsp");
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rs=request.getRequestDispatcher("ExpPage.jsp");
			rs.forward(request, response);
			
		}
		
	}

}
