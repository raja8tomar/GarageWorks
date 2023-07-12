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
@WebServlet("/changeUserPassword")
 
public class changeUserPassword extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String email=request.getParameter("email");
			String op=request.getParameter("o_password");
			String np=request.getParameter("n_password");
			String cp=request.getParameter("c_password");
			HttpSession session=request.getSession();
			if(np.equals(cp)) {
				UserDao d=new UserDao();
				 int result=d.changeUserPassword(email,op,np); 
				 
				 d.disconnect();
				 if(result !=0) {
					 session.setAttribute("msg","changed");
				 }else {
					 session.setAttribute("msg","wrong");
				 }
				 
			}else {
				session.setAttribute("msg","mismatch");
				
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
