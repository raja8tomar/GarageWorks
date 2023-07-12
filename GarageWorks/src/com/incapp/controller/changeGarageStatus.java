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
@WebServlet("/changeGarageStatus")
 
public class changeGarageStatus extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String email=request.getParameter("email");

			String status=request.getParameter("status");
				HttpSession session=request.getSession();
				AdminDao d=new AdminDao();
				 int result=d.changeGarageStatus(email,status); 
				 
				 d.disconnect();
				 if(result !=0) {
					 String r;
					  if(status.equalsIgnoreCase("Accepted")) {
					 r=com.incapp.mail.sentMail.SendMail(email, "Status Updated","Your Garage Has been Activated");
					 }
					  else {
							r= com.incapp.mail.sentMail.SendMail(email, "Status Updated","Your Garage Has been Rejected");
								  }
					  session.setAttribute("msg","Changed"+r);
						
					  
				 }else {
					 session.setAttribute("msg","Updation Falied");
					 	
					 	
				 }
				 
			
		
			 response.sendRedirect("adminHome.jsp");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rs=request.getRequestDispatcher("ExpPage.jsp");
			rs.forward(request, response);
			
		}
		
	}

}
