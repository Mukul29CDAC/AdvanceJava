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

@WebServlet("/updateCategories")
public class EditCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection db = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		PrintWriter out = response.getWriter();	
		
		HttpSession session = request.getSession();		
		ServletContext ctx = request.getServletContext();
		
		String url = ctx.getInitParameter("dbUrl");
		String id = (String) session.getAttribute("catId");
		int catId = Integer.parseInt(id);
		
		
		String name = request.getParameter("catName");
		String desc = request.getParameter("catDesc");
		String caturl = request.getParameter("catUrl");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			db = DriverManager.getConnection(url,"root","cdac");
			pst = db.prepareStatement("update categories set catId = ? ,catName = ?,catDesc = ?,catUrl = ? from categories where catId=?");
			pst.setInt(1, catId);
			pst.setString(2, name);
			pst.setString(3, desc);
			pst.setString(4,url);
			pst.setInt(5, catId);
		
			pst.executeUpdate();
			  
			
		
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	
		}
}
