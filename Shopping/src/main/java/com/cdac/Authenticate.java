package com.cdac;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cdac.object.AddToCartInf;
import com.cdac.object.AddToCartObject;
import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class Authenticate
 */
@WebServlet("/auth")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session= req.getSession();
		AddToCartInf cart=new AddToCartObject();
		session.setAttribute("cart", cart);
		
		ServletContext ctx = getServletContext();
		String url = ctx.getInitParameter("dbUrl");
		
		String username = req.getParameter("username");
		String password  = req.getParameter("password");
		
		Connection db = null;
		PreparedStatement pst  = null;
		ResultSet result = null;
		
		try {
			db = DriverManager.getConnection(url,"root","cdac");
			pst = db.prepareStatement("select * from users where username = ? and password = ?");
			pst.setString(1, username);
			pst.setString(2, password);
			result  = pst.executeQuery();
			
			if(result.next()) {
				session.setAttribute("username", username);
				res.sendRedirect("Category");
			}else {
				res.getWriter().println("Invalid users");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
