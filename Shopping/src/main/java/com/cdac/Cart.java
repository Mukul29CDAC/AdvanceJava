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
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cdac.object.AddToCartInf;
import com.cdac.object.AddToCartObject;
import com.cdac.object.Product;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session= request.getSession(false);
		int cid=Integer.parseInt(request.getParameter("cid"));
		int pid=Integer.parseInt(request.getParameter("pid"));
		
		PrintWriter out= response.getWriter();
		
		ServletContext ctx = getServletContext();
	    String dburl = ctx.getInitParameter("dbUrl");
		String dbUser = ctx.getInitParameter("dbUser");
		String dbPassword = ctx.getInitParameter("dbPassword");
		
		try {
	        Connection db =(Connection) ctx.getAttribute("db");
			PreparedStatement pst = db.prepareStatement("select * from products where catId = ? and prodid=?");
			
			pst.setInt(1, cid);
			pst.setInt(2, pid);
			
			ResultSet result =pst.executeQuery();
			Product product = new Product();
			if(result.next()) {
				product.setCatId(result.getInt("catid"));
				product.setProdId(result.getInt("prodid"));
				product.setProdName(result.getString("ProdName"));
				product.setProdPrice(result.getInt("prodPrice"));
				product.setProdImg(result.getString("ProdImg"));
			}
			
			System.out.println(product.toString());
			AddToCartInf cart=(AddToCartObject)session.getAttribute("cart");
			
			if(cart.add(product)) {
				response.sendRedirect("AddToCart");
			}else {
				out.println("<font color='red'>Something Went Wrong. Cannot add to Cart</font>");
				RequestDispatcher rd=request.getRequestDispatcher("Product?id="+cid);
				rd.include(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}
