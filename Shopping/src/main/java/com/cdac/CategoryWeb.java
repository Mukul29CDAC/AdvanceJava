package com.cdac;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/Category")
public class CategoryWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Connection connection = null;
	    PreparedStatement pst = null;
	    ResultSet result = null;

	    PrintWriter out = response.getWriter();

	    // Start of HTML output
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<style>");
	    out.println("/* Modern elegant table styles */");
	    out.println(":root {");
	    out.println("  --color-bg: #ffffff;");
	    out.println("  --color-text: #374151;");
	    out.println("  --color-text-muted: #6b7280;");
	    out.println("  --color-accent: #2563eb;");
	    out.println("  --color-border: #e5e7eb;");
	    out.println("  --font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Helvetica, Arial, sans-serif;");
	    out.println("  --border-radius: 0.75rem;");
	    out.println("  --shadow-color: rgba(0, 0, 0, 0.05);");
	    out.println("  --transition-duration: 0.3s;");
	    out.println("}");
	    out.println("table {");
	    out.println("  width: 70%;");
	    out.println("  border-collapse: separate;");
	    out.println("  border-spacing: 0;");
	    out.println("  background-color: var(--color-bg);");
	    out.println("  color: var(--color-text);");
	    out.println("  font-family: var(--font-family);");
	    out.println("  border-radius: var(--border-radius);");
	    out.println("  box-shadow: 0 4px 12px var(--shadow-color);");
	    out.println("  overflow: hidden;");
	    out.println("  margin: auto;");
	    out.println("  margin-bottom: 1.5rem;");
	    out.println("}");
	    out.println("thead {");
	    out.println("  background-color: #f9fafb;");
	    out.println("}");
	    out.println("thead th {");
	    out.println("  padding: 1rem 1.5rem;");
	    out.println("  text-align: left;");
	    out.println("  font-weight: 700;");
	    out.println("  font-size: 1rem;");
	    out.println("  color: var(--color-text-muted);");
	    out.println("  user-select: none;");
	    out.println("  border-bottom: 2px solid var(--color-border);");
	    out.println("  text-transform: uppercase;");
	    out.println("  letter-spacing: 0.05em;");
	    out.println("}");
	    out.println("tbody tr {");
	    out.println("  transition: background-color var(--transition-duration), transform var(--transition-duration);");
	    out.println("  cursor: default;");
	    out.println("}");
	    out.println("tbody tr:hover, tbody tr:focus-within {");
	    out.println("  background-color: #eff6ff;");
	    out.println("  transform: scale(1.02);");
	    out.println("  box-shadow: 0 4px 14px var(--shadow-color);");
	    out.println("  cursor: pointer;");
	    out.println("}");
	    out.println("tbody td {");
	    out.println("  padding: 1rem 1.5rem;");
	    out.println("  font-size: 0.95rem;");
	    out.println("  border-bottom: 1px solid var(--color-border);");
	    out.println("  color: var(--color-text);");
	    out.println("  vertical-align: middle;");
	    out.println("}");
	    out.println("tbody tr:last-child td {");
	    out.println("  border-bottom: none;");
	    out.println("}");
	    out.println("@media (max-width: 600px) {");
	    out.println("  thead th, tbody td {");
	    out.println("    padding: 0.75rem 1rem;");
	    out.println("    font-size: 0.9rem;");
	    out.println("  }");
	    out.println("}");
	    out.println("</style>");
	    out.println("</head>");
	    out.println("<body>");

	    try {
//	        db = DriverManager.getConnection(dburl,dbUser ,dbPassword );
	        connection =(Connection) getServletContext().getAttribute("db");
	        pst = connection.prepareStatement("select * from categories");
	        result = pst.executeQuery();
	        out.println("<table> <thead> <th>Category Id</th> <th>Category Name</th> <th>Category Description</th> <th>Category Image</th> </thead>");
	        out.println("<tbody>");
	        while (result.next()) {
	            out.println("<tr> <td>" + result.getString(1) + "</td>");
	            out.println("<td> <a style='text-decoration:none; color:black' href=Product?id="+result.getString(1)+">" + result.getString(2) + "</a></td>");
	            out.println("<td>" + result.getString(3) + "</td>");
	            out.println("<td>" + result.getString(4) + "</td>");
	            out.println("</tr>");
	        }
	        out.println("</tbody>");
	        out.println("</table>");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    out.println("</body>");
	    out.println("</html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
