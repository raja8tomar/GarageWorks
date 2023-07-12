package com.incapp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import com.incapp.dao.UserDao;


/**
 * Servlet implementation class AddEnquires
 */
@WebServlet("/SearchGarage")
@MultipartConfig
public class SearchGarage extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String state=request.getParameter("state");
			String city=request.getParameter("city");
			
			 
		
			 UserDao d=new UserDao();
			 ArrayList<HashMap> AllgarageDetails= d.getGarage(state,city);
			 
			 d.disconnect();
			 HttpSession session=request.getSession();
				
			 if(AllgarageDetails !=null) {
			 session.setAttribute("AllgarageDetails",AllgarageDetails);
			 response.sendRedirect("userHome.jsp");
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
