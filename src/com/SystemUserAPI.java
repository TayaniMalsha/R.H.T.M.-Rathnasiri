package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SystemUserAPI")
public class SystemUserAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	SystemUser Obj = new SystemUser();
	
    public SystemUserAPI() {
        super();
   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String output = Obj.readSystemUsers();
		
		//response.getWriter().write(output.toString());

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = Obj.insertSystemUser(request.getParameter("username"), 
				request.getParameter("email"),
				request.getParameter("password"));
		
		System.out.println(output);
		
		response.getWriter().write(output);
		
		
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map parse = getParseMap(request);
		
		String output = Obj.updateSystemUsers(parse.get("hiddenIDSave").toString(), 
				parse.get("username").toString(),
				parse.get("email").toString(),
				parse.get("password").toString());
		
		response.getWriter().write(output);
		
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map parse = getParseMap(request);
		
		String output = Obj.deleteSystemUser(parse.get("sId").toString());
		
		System.out.println(output);
		
		response.getWriter().write(output.toString());
	
	}
	
	
	private static Map getParseMap(HttpServletRequest request) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		try {
			Scanner sc = new Scanner(request.getInputStream(), "UTF-8");
			String query = sc.hasNext() ? sc.useDelimiter("\\A").next() : "";
			sc.close();
			
			String[] params = query.split("&");
			
			for(String param : params) {
				
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
