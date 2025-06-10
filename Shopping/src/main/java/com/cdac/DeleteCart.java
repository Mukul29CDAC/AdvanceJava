package com.cdac;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import com.cdac.object.AddToCartInf;
import com.cdac.object.AddToCartObject;
import com.cdac.object.Product;

/**
 * Servlet implementation class DeleteCart
 */
@WebServlet("/DeleteCart")
public class DeleteCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		AddToCartInf cart=(AddToCartObject)session.getAttribute("cart");
		 int id=Integer.parseInt(request.getParameter("id"));
		 
		 cart.removeProducts(id);
		 
		 response.sendRedirect("AddToCart");
	}

}
