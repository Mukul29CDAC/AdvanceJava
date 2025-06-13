package com.cdac;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String catId = request.getParameter("catID");
		String prodId = request.getParameter("prodId");
		String prodName = request.getParameter("prodName");
		String prodPrice = request.getParameter("prodPrice");
		String prodImg = request.getParameter("prodImg");
		
		try {
			Connection connection=(Connection) getServletContext().getAttribute("db");
			PreparedStatement psstatement = connection.prepareStatement("insert into products values (?,?,?,?,?)");
			
			psstatement.setString(1, catId);
			psstatement.setString(2, prodId);
			psstatement.setString(3, prodName);
			psstatement.setString(4, prodPrice);
			psstatement.setString(5, prodImg);

			if (psstatement.executeUpdate()>0) {
				response.getWriter().println("<font color='green'>Product Added</font><br>");
				RequestDispatcher rd=request.getRequestDispatcher("AdminPanel");	
				rd.include(request, response);
			}else {
				response.getWriter().println("<font color='red'>Something went wrong. Product was not Added</font>");
				response.getWriter().println("<font color='red'>Please Try Again</font>");
				RequestDispatcher rd=request.getRequestDispatcher("AdminPanel");	
				rd.include(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
