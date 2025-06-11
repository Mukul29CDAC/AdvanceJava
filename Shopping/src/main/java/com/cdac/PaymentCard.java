package com.cdac;

import jakarta.servlet.RequestDispatcher;
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
 * Servlet implementation class PaymentCard
 */
@WebServlet("/Payment")
public class PaymentCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		ServletContext ctx = getServletContext();
		String url = ctx.getInitParameter("dbUrl");
		

		AddToCartInf cart=(AddToCartObject) session.getAttribute("cart");

		double tmp =(Double)session.getAttribute("total");
		int total =(int) Math.round(tmp);;
		String username=(String) session.getAttribute("username");
		
		int cardno = Integer.parseInt(request.getParameter("cardno"));
		int expmon  = Integer.parseInt(request.getParameter("expmon"));
		int expyear = Integer.parseInt(request.getParameter("expyear"));
		int cardpin  = Integer.parseInt(request.getParameter("cardpin"));
		
		Connection db = null;
		PreparedStatement pst  = null;
		PreparedStatement pst2  = null;
		ResultSet result = null;
		
		try {
			db = DriverManager.getConnection(url,"root","cdac");
			pst = db.prepareStatement("select * from card where cardno = ? and expmon = ? and expyear = ? and pin = ? and balance > ?");
			pst.setInt(1, cardno);
			pst.setInt(2, expmon);
			pst.setInt(3, expyear);
			pst.setInt(4, cardpin);
			pst.setInt(5, total);
			
			result  = pst.executeQuery();
			
			if(result.next()) {
				int balance=result.getInt("balance") - total;
				pst = db.prepareStatement("update card set balance=? where cardno=?");
				pst.setInt(1, balance);
				pst.setInt(2, result.getInt("cardno"));
				if(pst.executeUpdate()>0) {
				
				
				pst2 = db.prepareStatement("INSERT INTO transactions (`username`, `cardno`, `amount`, `status`) VALUES (?,?,?,?);");
				pst2.setString(1, username);
				pst2.setInt(2, result.getInt("cardno"));
				pst2.setInt(3, total);
				pst2.setString(4, "true");
				pst2.executeUpdate();
				
				cart.removeAllProducts();
				response.getWriter().println("<font color='green'>Payment Successfully . Continue Shopping </font>");
				RequestDispatcher rd=request.getRequestDispatcher("Category");
				rd.include(request, response);
				}else {
					response.getWriter().println("<font color='red'>Payment Unsuccessfully . Please try again </font>");
					RequestDispatcher rd=request.getRequestDispatcher("AddToCart");
					rd.include(request, response);
				}
			}else {
				response.getWriter().println("<font color='red'>Payment Unsuccessfully . Please try again </font>");
				response.sendRedirect("AddToCart");
//				RequestDispatcher rd=request.getRequestDispatcher("AddToCart");
//				rd.include(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
