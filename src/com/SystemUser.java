package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SystemUser {
	
	private Connection connect() {
		Connection con = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gadgetbadget?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			
		}catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
		
		return con;
	}
	
	public String readSystemUsers() {
		
		String output = ""; 
		 
		try   {    
			  
			  Connection con = connect(); 
		 
			   if (con == null)    {
				   return "Error while connecting to the database.."; 
				   
			   } 
			    
		   // Prepare the html table to be displayed    
		  output = "<table border='1'><tr>"
		  		+ "<th>User Name</th>"
		  		+ "<th>Email</th>"
		  		+ "<th>Password</th>"
		  		+ "<th>Update</th>"
		  		+ "<th>Remove</th></tr>"; 
		 
		   String query = "select * from system_editor";    
		   Statement stmt = con.createStatement();    
		   ResultSet rs = stmt.executeQuery(query); 
		 
		   // iterate through the rows in the result set    
		   while (rs.next())    {     
			   String sId = Integer.toString(rs.getInt("sid"));     
			   String username = rs.getString("username");     
			   String email = rs.getString("email");     
			   String password = rs.getString("password");     
		 
		    // Add into the html table
			   output += "<tr><td><input id='hiddenIDUpdate' name='hiddenIDUpdate' type='hidden' value='" + sId + "'>"+ username + "</td>"; 
			   output += "<td>" + email + "</td>";     
			   output += "<td>" + password + "</td>";     
		 
		    // buttons     
			   output += "<td><input name='btnUpdate' type='button'"
			   		+ "value='Update' class='btnUpdate btn btn-secondary'></td>" 
			   		+ "<td><input name='btnRemove' type='button' value='Remove'"
			   		+ "class='btnRemove btn btn-danger' data-sid='"      
			   		+ sId +"'></td></tr>";    
		   } 
		 
		   con.close(); 
		 
		   // Complete the html table    
		   output += "</table>";   
		   
		}catch (Exception e)   {    
			output = "Error while reading the Users.";    
			System.err.println(e.getMessage());   
		} 
		 
		  return output; 
		  
	}
	
	public String insertSystemUser(String username, String email, String password) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			if(con == null) {
				return "Error while conncting to the database for inserting..";
				
			}
			
			String query = "INSERT INTO system_editor(`username`, `email`, `password`) VALUES (?,?,?)";
			
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, password);
			
			ps.executeUpdate();
			ps.close();
			
			String newSystemUsers = readSystemUsers();
			output = "{\"status\":\"success\", \"data\": \""+newSystemUsers+"\"}";
		//	output = "Insert Successfully";
			
		}catch (Exception e) {
			// TODO: handle exception
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the users.\"}";
			System.out.println(e);
		}
		
		return output;
		
	}
	
	public String updateSystemUsers(String ID, String username, String email, String password)  {   
		
		String output = ""; 
	 
		try   {    
			Connection con = connect(); 
	 
	   if (con == null)    {
		   return "Error while connecting to the database for updating."; 
	   } 
	 
	   // create a prepared statement    
	   String query = "UPDATE system_editor SET username=?,email=?,password=?"
	   		+ "WHERE sid=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	   preparedStmt.setString(1, username);    
	   preparedStmt.setString(2, email);    
	   preparedStmt.setString(3, password);       
	   preparedStmt.setInt(4, Integer.parseInt(ID)); 
	 
	   // execute the statement    
	   preparedStmt.execute();    
	   con.close(); 
	 
	   String newSystemUsers = readSystemUsers();
	   output = "{\"status\":\"success\", \"data\": \""+newSystemUsers+"\"}";
	//   output = "Updated successfully";   
	   
		}catch (Exception e)   {    
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the user.\"}";    
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	  
	}
	
	public String deleteSystemUser(String sId)  {   
		
		String output = ""; 
	 
	  try   {    
		  Connection con = connect(); 
	 
	   if (con == null)    {
		   return "Error while connecting to the database for deleting."; 
	   } 
	 
	   // create a prepared statement    
	   String query = "delete from system_editor where sid=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	   preparedStmt.setInt(1, Integer.parseInt(sId)); 
	 
	   // execute the statement    
	   preparedStmt.execute();    
	   con.close(); 
	 
	   String systemUsers = readSystemUsers();
	   output = "{\"status\":\"success\", \"data\": \""+systemUsers+"\"}";  
	   
	  }catch (Exception e)   {    
		  output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";    
		  System.err.println(e.getMessage());   
	  } 
	 
	  return output;  
	  
	}

}
