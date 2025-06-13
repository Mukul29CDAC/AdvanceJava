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
import com.cdac.object.Product;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/UpdateProduct")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String catid=request.getParameter("cid");
		String prodid=request.getParameter("pid");
		
		PreparedStatement pst  = null;
		ResultSet result = null;
		
		try {
			Connection connection=(Connection) getServletContext().getAttribute("db");
			
			pst = connection.prepareStatement("select * from products where catId = ? and prodID = ?");
			pst.setString(1, catid);
			pst.setString(2, prodid);
			result  = pst.executeQuery();
			
			if(result.next()) {
				
				Product product=new Product(result.getInt(1), result.getInt(2), result.getString(3), result.getInt(4), result.getString(5));
				
				HttpSession session= request.getSession();
				
				session.setAttribute("product", product);
				response.sendRedirect("ChangeProduct.jsp");
			}else {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
