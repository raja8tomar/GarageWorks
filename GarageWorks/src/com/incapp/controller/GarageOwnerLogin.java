package com.incapp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.incapp.dao.GarageDao;


/**
 * Servlet implementation class AddEnquires
 */
@WebServlet("/GarageOwnerLogin")
@MultipartConfig
public class GarageOwnerLogin extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String email=request.getParameter("Email");
			String password=request.getParameter("Password");
			
			 
		
			 GarageDao d=new GarageDao();
			 HashMap garageOwnerDetails= d.getgarageOwnerLogin(email,password);
			 
			 d.disconnect();
			 HttpSession session=request.getSession();
				
			 if(garageOwnerDetails !=null) {
			 session.setAttribute("garageOwnerDetails",garageOwnerDetails);
			 response.sendRedirect("garageOwnerHome.jsp");
			 }
			 else {
				 session.setAttribute("msg","Invalid");
				 response.sendRedirect("user.jsp");
				
			 }
			
		}
		catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rs=request.getRequestDispatcher("ExpPage.jsp");
			rs.forward(request, response);
			
		}
		
	}

}
