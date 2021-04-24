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


@WebServlet("/SponsorsAPI")
public class SponsorsAPI extends HttpServlet {
	                                                                
	private static final long serialVersionUID = 1L;
	
	Sponsors Obj = new Sponsors();
	
    public SponsorsAPI() {
        super();
   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String output = Obj.readSponsors();
		
		//response.getWriter().write(output.toString());


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = Obj.insertSponsors(request.getParameter("username"), 
				request.getParameter("company"),
				request.getParameter("project"));
		 
		System.out.println(output);
		
		response.getWriter().write(output);
		
		
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map parse = getParseMap(request);
		
		String output = Obj.updateSponsors(parse.get("hiddenIDSave").toString(), 
				parse.get("username").toString(),
				parse.get("company").toString(),
				parse.get("project").toString());
		
		response.getWriter().write(output);
		
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map parse = getParseMap(request);

		String output = Obj.deleteSponsors(parse.get("Id").toString());
		
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
