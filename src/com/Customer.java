package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {
	
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
	
	public String readCustomer() {
		
		String output = ""; 
		 
		try   {    
			  
			  Connection con = connect(); 
		 
			   if (con == null)    {
				   return "Error while connecting to the database.."; 
				   
			   } 
			    
		   // Prepare the html table to be displayed    
		  output = "<table border='1'><tr>"
		  		+ "<th>Name</th>"
		  		+ "<th>Email</th>"
		  		+ "<th>Address</th>"
		  		+ "<th>Update</th>"
		  		+ "<th>Remove</th></tr>"; 
		 
		   String query = "select * from buyer";    
		   Statement stmt = con.createStatement();    
		   ResultSet rs = stmt.executeQuery(query); 
		 
		   // iterate through the rows in the result set    
		   while (rs.next()) {     
			   String cId = Integer.toString(rs.getInt("buyerID"));     
			   String name = rs.getString("name");     
			   String email = rs.getString("email");     
			   String address = rs.getString("address");     
		 
		    // Add into the html table
			   output += "<tr><td><input id='hiddenIDUpdate' name='hiddenIDUpdate' type='hidden' value='" + cId + "'>"+ name + "</td>"; 
			   output += "<td>" + email + "</td>";     
			   output += "<td>" + address + "</td>";     
		 
		    // buttons     
			   output += "<td><input name='btnUpdate' type='button'"
			   		+ "value='Update' class='btnUpdate btn btn-secondary'></td>" 
			   		+ "<td><input name='btnRemove' type='button' value='Remove'"
			   		+ "class='btnRemove btn btn-danger' data-cid='"      
			   		+ cId +"'></td></tr>";    
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
	
	public String insertCustomer(String name, String email, String address) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			if(con == null) {
				return "Error while conncting to the database for inserting..";
				
			}
			
			String query = "INSERT INTO buyer(`name`, `email`, `address`) VALUES (?,?,?)";
			
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, address);
			
			ps.executeUpdate();
			ps.close();
			
			String newCustomer = readCustomer();
			output = "{\"status\":\"success\", \"data\": \""+newCustomer+"\"}";
		//	output = "Insert Successfully";
			
		}catch (Exception e) {
			// TODO: handle exception
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the users.\"}";
			System.out.println(e);
		}
		
		return output;
		
	}
	
	public String updateCustomer(String ID, String name, String email, String address)  {   
		
		String output = ""; 
	 
		try   {    
			Connection con = connect(); 
	 
	   if (con == null)    {
		   return "Error while connecting to the database for updating."; 
	   } 
	 
	   // create a prepared statement    
	   String query = "UPDATE buyer SET name=?,email=?,address=?"
	   		+ "WHERE buyerID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	   preparedStmt.setString(1, name);    
	   preparedStmt.setString(2, email);    
	   preparedStmt.setString(3, address);       
	   preparedStmt.setInt(4, Integer.parseInt(ID)); 
	 
	   // execute the statement    
	   preparedStmt.execute();    
	   con.close(); 
	 
	   String newCustomer = readCustomer();
	   output = "{\"status\":\"success\", \"data\": \""+newCustomer+"\"}";
	//   output = "Updated successfully";   
	   
		}catch (Exception e)   {    
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the user.\"}";    
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	  
	}
	
	public String deleteCustomer(String cId)  {   
		
		String output = ""; 
	 
	  try   {    
		  Connection con = connect(); 
	 
	   if (con == null)    {
		   return "Error while connecting to the database for deleting."; 
	   } 
	 
	   // create a prepared statement    
	   String query = "delete from buyer where buyerID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	   preparedStmt.setInt(1, Integer.parseInt(cId)); 
	 
	   // execute the statement    
	   preparedStmt.execute();    
	   con.close(); 
	 
	   String newCustomer = readCustomer();
	   output = "{\"status\":\"success\", \"data\": \""+newCustomer+"\"}";  
	   
	  }catch (Exception e)   {    
		  output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";    
		  System.err.println(e.getMessage());   
	  } 
	 
	  return output;  
	  
	}

}
