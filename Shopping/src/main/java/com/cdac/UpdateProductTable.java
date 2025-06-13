package com.cdac;

import jakarta.servlet.RequestDispatcher;
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

/**
 * Servlet implementation class UpdateProductTable
 */
@WebServlet("/UpdateProductTable")
public class UpdateProductTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		int catId = Integer.parseInt(request.getParameter("catId"));
		int prodId = Integer.parseInt(request.getParameter("prodId"));
		String prodName = request.getParameter("prodName");
		int prodPrice = Integer.parseInt(request.getParameter("prodPrice"));
		String prodImg = request.getParameter("prodImg");
		
		PreparedStatement psstatement=null;
		
		try {
			Connection connection=(Connection) getServletContext().getAttribute("db");
			psstatement = connection.prepareStatement("Update products set prodName=?,prodPrice=?,prodImg=? where catID=? and prodid=?");
			
			psstatement.setString(1, prodName);
			psstatement.setInt(2, prodPrice);
			psstatement.setString(3, prodImg);
			psstatement.setInt(4, catId);
			psstatement.setInt(5, prodId);

			if (psstatement.executeUpdate()>0) {
				response.getWriter().println("<font color='green'>Product Updated</font><br>");
				RequestDispatcher rd=request.getRequestDispatcher("AdminPanel");	
				rd.include(request, response);
			}else {
				response.getWriter().println("<font color='red'>Something went wrong. Product was not Updated</font>");
				response.getWriter().println("<font color='red'>Please Try Again</font>");
				RequestDispatcher rd=request.getRequestDispatcher("AdminPanel");	
				rd.include(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				try {
					if(psstatement!=null)
					psstatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}
