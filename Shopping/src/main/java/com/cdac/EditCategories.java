package com.cdac;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/EditCategories")
public class EditCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PreparedStatement pst = null;
		
		HttpSession session = request.getSession();		
		
		String id = (String) session.getAttribute("catId");
		int catId = Integer.parseInt(id);
		
		
		String name = request.getParameter("catName");
		String desc = request.getParameter("catDesc");
		String caturl = request.getParameter("catUrl");
		
		try {
			Connection connection=(Connection) getServletContext().getAttribute("db");
			pst = connection.prepareStatement("update categories set catId = ? ,catName = ?,catDesc = ?,catUrl = ? from categories where catId=?");
			pst.setInt(1, catId);
			pst.setString(2, name);
			pst.setString(3, desc);
			pst.setString(4,caturl);
			pst.setInt(5, catId);
		
			pst.executeUpdate();
			  
			
		
			
		}catch(SQLException  e) {
			e.printStackTrace();
		}

	
		}
}
