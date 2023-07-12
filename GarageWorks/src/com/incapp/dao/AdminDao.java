package com.incapp.dao;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminDao {
        private Connection c;
        public AdminDao() throws SQLException,ClassNotFoundException{
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	c=DriverManager.getConnection("jdbc:mysql://localhost:3306/garage_works","root","Incapp@12");
        }
        public void disconnect() throws SQLException{
        	c.close();
        }
       
        public boolean getAdminLogin(String id,String password) throws SQLException{
        	PreparedStatement p=c.prepareStatement("select * from admin where id=? and password=?");
        	p.setString(1,id);
        	p.setString(2, password);
        	ResultSet rs=p.executeQuery();
        	
        	if(rs.next()) {
        		 
        		return true;
        	}
        	else {
        		return false;
        	}
        	
        }
        public ArrayList<HashMap> getGarageStatus(String status)throws SQLException{
        	PreparedStatement p=c.prepareStatement("select * from garage where status=?");
        	p.setString(1,status);
        	ResultSet rs=p.executeQuery();
        	ArrayList<HashMap> AllgarageOwnerDetails=new ArrayList<HashMap>();
        	while(rs.next()) {
        		HashMap garageOwnerDetails=new HashMap();
        		garageOwnerDetails.put("email",rs.getString("email"));

        		garageOwnerDetails.put("name",rs.getString("name"));
        		garageOwnerDetails.put("gname",rs.getString("gname"));
        		
        		garageOwnerDetails.put("phone",rs.getString("phone"));
        		garageOwnerDetails.put("state",rs.getString("state"));
        		garageOwnerDetails.put("city",rs.getString("city"));
        		garageOwnerDetails.put("area",rs.getString("area"));
        		garageOwnerDetails.put("shop_no",rs.getString("shop_no"));
        		garageOwnerDetails.put("password",rs.getString("password"));
        		garageOwnerDetails.put("photo1",rs.getString("photo1"));
        		garageOwnerDetails.put("photo2",rs.getString("photo2"));
        		garageOwnerDetails.put("status",rs.getString("status"));
        		AllgarageOwnerDetails.add(garageOwnerDetails);
        	}
        		

    		return AllgarageOwnerDetails;
        }
        public HashMap getGarageEmail(String email)throws SQLException{
        	PreparedStatement p=c.prepareStatement("select * from garage where email=?");
        	p.setString(1,email);
        	ResultSet rs=p.executeQuery();
        	
        		if(rs.next()) {
        			HashMap garageOwnerDetails=new HashMap();
                	
        			garageOwnerDetails.put("email",rs.getString("email"));

        		garageOwnerDetails.put("name",rs.getString("name"));
        		garageOwnerDetails.put("gname",rs.getString("gname"));
        		
        		garageOwnerDetails.put("phone",rs.getString("phone"));
        		garageOwnerDetails.put("state",rs.getString("state"));
        		garageOwnerDetails.put("city",rs.getString("city"));
        		garageOwnerDetails.put("area",rs.getString("area"));
        		garageOwnerDetails.put("shop_no",rs.getString("shop_no"));
        		garageOwnerDetails.put("password",rs.getString("password"));
        		garageOwnerDetails.put("photo1",rs.getString("photo1"));
        		garageOwnerDetails.put("photo2",rs.getString("photo2"));
        		garageOwnerDetails.put("status",rs.getString("status"));

        		return garageOwnerDetails;
        	}else {
        		return null;
        	}
        		
        		

        }
        public int changeGarageStatus(String email,String status) throws SQLException{
        	PreparedStatement p=c.prepareStatement("update garage set status=? where email=?");
        	p.setString(1,status);
        	p.setString(2, email);
        	
            
            int result=p.executeUpdate();
        	
        	return result;
        	}
        public String[] getAdminDetails() throws SQLException{
        	PreparedStatement p=c.prepareStatement("select * from admin");
        	ResultSet rs=p.executeQuery();
        	String adminDetails[]=new String[2];
        	
        	if(rs.next()) {
        		adminDetails[0]=rs.getString("id");
        		adminDetails[1]=rs.getString("password");
        		
            	
        	}
        	return adminDetails;
        }

        
}
