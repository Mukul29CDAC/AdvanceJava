package com.cdac;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



@WebServlet("/addCategory")
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.getWriter().println("hello Add");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String id = request.getParameter("catId");
			int catId = Integer.parseInt(id);
			
			String name = request.getParameter("catName");
			String desc = request.getParameter("catDesc");
			String caturl = request.getParameter("catUrl");
			
			Connection db = null;
			PreparedStatement pst = null;
			
			ServletContext ctx = request.getServletContext();
			String url = ctx.getInitParameter("dbUrl");
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				db = DriverManager.getConnection(url,"root","cdac");
				
				pst = db.prepareStatement("insert into categories values(?,?,?,?)");
				pst.setInt(1, catId);
				pst.setString(2, name);
				pst.setString(3, desc);
				pst.setString(4, caturl);
				
				pst.executeUpdate();
			}catch(SQLException | ClassNotFoundException e) {
				
			}
			
			System.out.println(catId+ " " + name + " "+ desc + " " + url);
		
	}

}
