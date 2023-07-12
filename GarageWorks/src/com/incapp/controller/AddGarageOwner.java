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
@WebServlet("/AddGarageOwner")
@MultipartConfig
public class AddGarageOwner extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String email=request.getParameter("Email");
			String name=request.getParameter("Name");
			String gname=request.getParameter("gname");
			String city=request.getParameter("city");
			String state=request.getParameter("state");
			String area=request.getParameter("area");
			String shop_no=request.getParameter("shop_no");
			
			String phone=request.getParameter("Phone");
			String password=request.getParameter("Password");
			InputStream photo1=null;
			 Part p1=request.getPart("Photo1");
			 if(p1 !=null) {
				 photo1=p1.getInputStream();
			 }
			 InputStream photo2=null;
			 Part p2=request.getPart("Photo2");
			 if(p1 !=null) {
				 photo2=p2.getInputStream();
			 }
			 HashMap garage=new HashMap();
			 garage.put("email",email);
			 garage.put("name",name);
			 garage.put("gname",gname);
				
			 garage.put("phone",phone);
			 garage.put("state",state);
			 garage.put("city",city);
			 garage.put("area",area);
			 garage.put("shop_no",shop_no);
				
			 garage.put("password",password);
			 garage.put("photo1",photo1);
			 garage.put("photo2",photo2);
				
			 
		
			 GarageDao d=new GarageDao();
			 String result=d.setGarage(garage);
			 
			 d.disconnect();
			 HttpSession session=request.getSession();
			 session.setAttribute("msg",result);
			 response.sendRedirect("garageOwner.jsp");
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rs=request.getRequestDispatcher("ExpPage.jsp");
			rs.forward(request, response);
			
		}
		
	}

}
