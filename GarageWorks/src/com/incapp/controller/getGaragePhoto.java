package com.incapp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
@WebServlet("/getGaragePhoto")

public class getGaragePhoto extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String email=request.getParameter("email");

			String photo_no=request.getParameter("photo_no");
		
			 GarageDao d=new GarageDao();
			 byte photo[]= d.getGaragePhoto(email,photo_no);
				
			 d.disconnect();
			 
			 
			
			response.getOutputStream().write(photo);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rs=request.getRequestDispatcher("ExpPage.jsp");
			rs.forward(request, response);
			
		}
		
	}

}
