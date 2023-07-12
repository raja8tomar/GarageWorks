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
@WebServlet("/AddUser")
@MultipartConfig
public class AddUser extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String email=request.getParameter("Email");
			String name=request.getParameter("Name");
			String phone=request.getParameter("Phone");
			String password=request.getParameter("Password");
			InputStream photo=null;
			 Part p=request.getPart("Photo");
			 if(p !=null) {
				 photo=p.getInputStream();
			 }
			 HashMap user=new HashMap();
			 user.put("email",email);
			 user.put("name",name);
			 user.put("phone",phone);	
			 user.put("password",password);
              user.put("photo",photo);
				
			 
		
			 UserDao d=new UserDao();
			 String result=d.setUser(user);
			 
			 d.disconnect();
			 HttpSession session=request.getSession();
			 session.setAttribute("msg",result);
			 response.sendRedirect("user.jsp");
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rs=request.getRequestDispatcher("ExpPage.jsp");
			rs.forward(request, response);
			
		}
		
	}

}
