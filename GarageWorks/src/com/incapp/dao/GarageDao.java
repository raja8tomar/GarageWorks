package com.incapp.dao;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class GarageDao {
        private Connection c;
        public GarageDao() throws SQLException,ClassNotFoundException{
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	c=DriverManager.getConnection("jdbc:mysql://localhost:3306/garage_works","root","Incapp@12");
        }
        public void disconnect() throws SQLException{
        	c.close();
        }
       
        public String setGarage(HashMap garage) throws SQLException {
        	try {
        		PreparedStatement p=c.prepareStatement("insert into garage (email,name,gname,phone,state,city,area,shop_no,password,photo1,photo2,status) values(?,?,?,?,?,?,?,?,?,?,?,'Pending')");
            	p.setString(1, (String)garage.get("email"));
            	p.setString(2, (String)garage.get("name"));
            	p.setString(3, (String)garage.get("gname"));
            	p.setString(4, (String)garage.get("phone"));
            	p.setString(5, (String)garage.get("state"));
            	p.setString(6, (String)garage.get("city"));
            	p.setString(7, (String)garage.get("area"));

            	p.setString(8, (String)garage.get("shop_no"));
            	p.setString(9, (String)garage.get("password"));
            	p.setBinaryStream(10, (InputStream)garage.get("photo1"));
            	p.setBinaryStream(11, (InputStream)garage.get("photo2"));
                
         
            	p.executeUpdate();
            	return "Success";
        	}
        	catch(SQLIntegrityConstraintViolationException e) {
        		return "failed";  
        		
        	}
        }
        public HashMap getgarageOwnerLogin(String email,String password) throws SQLException{
        	PreparedStatement p=c.prepareStatement("select * from garage where email=? and password=?");
        	p.setString(1, email);
        	p.setString(2, password);
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
        	}
        	else {
        		return null;
        	}
        	
        }
        public byte[] getGaragePhoto(String email, String photo_no) throws SQLException{
        	PreparedStatement p=c.prepareStatement("select photo1,photo2 from garage where email=?");
        	p.setString(1, email);
        
        	
        	ResultSet rs=p.executeQuery();
        	rs.next() ;
        	byte photo[];
        	if(photo_no.equalsIgnoreCase("photo1")) 
              photo=rs.getBytes("photo1");
        	else 
        	    photo=rs.getBytes("photo2");
            	
        	
        	return photo;
        }
        public String setService(String services,String email) throws SQLException{
        	PreparedStatement p=c.prepareStatement("insert into services (gemail,services) values(?,?)");
        	p.setString(1, email);
        	p.setString(2, services);
            p.executeUpdate();
        
        	
        	return "success";
        }
        public String deleteService(int id) throws SQLException{
        	PreparedStatement p=c.prepareStatement("delete from services where id=?");
        	p.setInt(1, id);
            p.executeUpdate();
        
        	
        	return "success";
        }
        public ArrayList<HashMap> getServices(String email) throws SQLException{
        	PreparedStatement p=c.prepareStatement("select * from services where gemail=?");
        	p.setString(1, email);
        	
        	ResultSet rs=p.executeQuery();
        	ArrayList<HashMap> services=new ArrayList();
        	
        	while(rs.next()) {
                HashMap service=new HashMap();
        		service.put("id",rs.getInt("id"));
        		service.put("services",rs.getString("services"));
        		services.add(service);	
        	
        	}
        	return services;
        	
        }
        
        

}
