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

import com.incapp.dao.AdminDao;
import com.incapp.dao.UserDao;


/**
 * Servlet implementation class AddEnquires
 */
@WebServlet("/AdminLogin")
@MultipartConfig
public class AdminLogin extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String id=request.getParameter("id");
			String password=request.getParameter("Password");
			
			 
		
			 AdminDao d=new AdminDao();
			 boolean r= d.getAdminLogin(id,password);
			 
			 d.disconnect();
			 HttpSession session=request.getSession();
				
			 if(r) {
			session.setAttribute("aname", "Rajat");
			 response.sendRedirect("adminHome.jsp");
			 }
			 else {
				 session.setAttribute("msg","Invalid");
				 response.sendRedirect("admin.jsp");
				
			 }
			
		}
		catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rs=request.getRequestDispatcher("ExpPage.jsp");
			rs.forward(request, response);
			
		}
		
	}

}
