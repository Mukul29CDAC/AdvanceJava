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
public class UpdateCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection db = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();	
		
		ServletContext ctx = request.getServletContext();
		String url = ctx.getInitParameter("dbUrl");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			db = DriverManager.getConnection(url,"root","cdac");
			pst = db.prepareStatement("select * from categories");
			
			result = pst.executeQuery();
			   out.println("<html>");
			    out.println("<head>");
			    out.println("<style>");
			    out.println("</style>");
			    out.println("</head>");
			    out.println("<body>");
			    out.println("<table> <thead> <th>Category Id</th> <th>Category Name</th> <th>Category Description</th> <th>Category Image</th> </thead>");
		        out.println("<tbody>");
			while(result.next()) {
				 out.println("<tr> <td>" + result.getInt(1) + "</td>");
		            out.println("<td> <a style='text-decoration:none; color:black' href=Product?id="+result.getString(1)+">" + result.getString(2) + "</a></td>");
		            out.println("<td>" + result.getString(3) + "</td>");
		            out.println("<td>" + result.getString(4) + "</td>");
		            out.println("<td><a href ='updatecategories.html'>"+ "Edit"+ "</a></td>");
		            out.println("</tr>");
		            session.setAttribute("catId",  result.getInt(1));
			}
			  out.println("</tbody>");
		        out.println("</table>");
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	    out.println("</body>");
	    out.println("</html>");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
