package com.incapp.dao;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDao {
        private Connection c;
        public UserDao() throws SQLException,ClassNotFoundException{
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	c=DriverManager.getConnection("jdbc:mysql://localhost:3306/garage_works","root","Incapp@12");
        }
        public void disconnect() throws SQLException{
        	c.close();
        }
        public void setEnquiry(String name,String phone,String email) throws SQLException {
        	PreparedStatement p=c.prepareStatement("insert into enquries (name,phone,email) values(?,?,?)");
        	p.setString(1, name);
        	p.setString(2, phone);
        	p.setString(3, email);
        	p.executeUpdate();
        }
        public String setUser(HashMap user) throws SQLException {
        	try {
        		PreparedStatement p=c.prepareStatement("insert into user (email,name,phone,password,photo) values(?,?,?,?,?)");
            	p.setString(1, (String)user.get("email"));
            	p.setString(2, (String)user.get("name"));
            	p.setString(3, (String)user.get("phone"));
            	p.setString(4, (String)user.get("password"));
            	p.setBinaryStream(5, (InputStream)user.get("photo"));
         
            	p.executeUpdate();
            	return "Success";
        	}
        	catch(SQLIntegrityConstraintViolationException e) {
        		return "failed";  
        		
        	}
        }
        public HashMap getUserLogin(String email,String password) throws SQLException{
        	PreparedStatement p=c.prepareStatement("select * from user where email=? and password=?");
        	p.setString(1, email);
        	p.setString(2, password);
        	ResultSet rs=p.executeQuery();
        	
        	if(rs.next()) {
        		HashMap userDetails=new HashMap();
        		userDetails.put("name",rs.getString("name"));
                userDetails.put("email",rs.getString("email"));
                userDetails.put("phone",rs.getString("phone"));
        		return userDetails;
        	}
        	else {
        		return null;
        	}
        	
        }
        public byte[] getUserPhoto(String email) throws SQLException{
        	PreparedStatement p=c.prepareStatement("select photo from user where email=? ");
        	p.setString(1, email);
        	ResultSet rs=p.executeQuery();
        	rs.next() ;
        	byte photo[]=rs.getBytes("photo");
        	return photo;
        }
        public String uploadUserPhoto(String email,InputStream photo) throws SQLException {
        	PreparedStatement p=c.prepareStatement("update user set photo=? where email=? ");
        	
        	p.setBinaryStream(1,photo);
        	p.setString(2, email);
            p.executeUpdate();
        	
        	return "success";
        }
        public int changeUserPassword(String email,String op,String np) throws SQLException{
        	PreparedStatement p=c.prepareStatement("update user set password=? where email=? and password=?");
        	p.setString(1, np);
        	p.setString(2, email);
        	p.setString(3, op);
            
            int result=p.executeUpdate();
        	
        	return result;
        	}
        public ArrayList<HashMap> getGarage(String state,String city)throws SQLException{
        	PreparedStatement p=c.prepareStatement("select * from garage where state=? and city=? and status='Accepted'");
        	p.setString(1,state);
        	p.setString(2,city);
        	
        	ResultSet rs=p.executeQuery();
        	ArrayList<HashMap> AllgarageDetails=new ArrayList<HashMap>();
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
        				AllgarageDetails.add(garageOwnerDetails);
        	}
        		

    		return AllgarageDetails;
        }

}
