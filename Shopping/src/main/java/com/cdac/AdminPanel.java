package com.cdac;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AdminPanel")
public class AdminPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
		
			response.setContentType("text/html;charset=UTF-8");
			  out.println("<!DOCTYPE html>");
	            out.println("<html lang=\"en\">");
	            out.println("<head>");
	            out.println("    <meta charset=\"UTF-8\">");
	            out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
	            out.println("    <title>Admin Panel</title>");
	            out.println("    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@600&display=swap\" rel=\"stylesheet\">");

            out.println("</head>");
            out.println("<body>");
            out.println("<a href=\"category\">Add Categories</a>");
            out.println("<a href=\"category\">Update Categories</a>");
            out.println("<a href=\"AddProduct.jsp\">Add Product</a>");	
            out.println("<a href=\"SelectCategory.jsp\">Update Product</a>");
           

            out.println("</body>");
            out.println("</html>");
		 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
