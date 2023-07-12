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

import com.incapp.dao.UserDao;


/**
 * Servlet implementation class AddEnquires
 */
@WebServlet("/uploadUserPhoto")
@MultipartConfig
public class uploadUserPhoto extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String email=request.getParameter("email");
				InputStream photo=null;
			 Part p=request.getPart("Photo");
			 if(p !=null) {
				 photo=p.getInputStream();
			 }
			
			 UserDao d=new UserDao();
			 String result=d.uploadUserPhoto(email,photo); 
			 
			 d.disconnect();
			 HttpSession session=request.getSession();
				
			 if(result.equalsIgnoreCase("success")) {
			 session.setAttribute("msg","success");
			
			 }
			 else {
				 session.setAttribute("msg","failed");
				 
			 }
			 response.sendRedirect("userProfile.jsp");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rs=request.getRequestDispatcher("ExpPage.jsp");
			rs.forward(request, response);
			
		}
		
	}

}
