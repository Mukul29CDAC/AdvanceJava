<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	    <head>
	    <style>
	    /* Modern elegant table styles */
	    :root {
	      --color-bg: #ffffff;
	      --color-text: #374151;
	      --color-text-muted: #6b7280;
	      --color-accent: #2563eb;
	      --color-border: #e5e7eb;
	      --font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Helvetica, Arial, sans-serif;
	      --border-radius: 0.75rem;
	      --shadow-color: rgba(0, 0, 0, 0.05);
	      --transition-duration: 0.3s;
	    }
	    table {
	      width: 70%;
	      border-collapse: separate;
	      border-spacing: 0;
	      background-color: var(--color-bg);
	      color: var(--color-text);
	      font-family: var(--font-family);
	      border-radius: var(--border-radius);
	      box-shadow: 0 4px 12px var(--shadow-color);
	      overflow: hidden;
	      margin: auto;
	      margin-bottom: 1.5rem;
	    }
	    thead {
	      background-color: #f9fafb;
	    }
	    thead th {
	      padding: 1rem 1.5rem;
	      text-align: left;
	      font-weight: 700;
	      font-size: 1rem;
	      color: var(--color-text-muted);
	      user-select: none;
	      border-bottom: 2px solid var(--color-border);
	      text-transform: uppercase;
	      letter-spacing: 0.05em;
	    }
	    tbody tr {
	      transition: background-color var(--transition-duration), transform var(--transition-duration);
	      cursor: default;
	    }
	    tbody tr:hover, tbody tr:focus-within {
	      background-color: #eff6ff;
	      transform: scale(1.02);
	      box-shadow: 0 4px 14px var(--shadow-color);
	      cursor: pointer;
	    }
	    tbody td {
	      padding: 1rem 1.5rem;
	      font-size: 0.95rem;
	      border-bottom: 1px solid var(--color-border);
	      color: var(--color-text);
	      vertical-align: middle;
	    }
	    tbody tr:last-child td {
	      border-bottom: none;
	    }
	    @media (max-width: 600px) {
	      thead th, tbody td {
	        padding: 0.75rem 1rem;
	        font-size: 0.9rem;
	      }
	    }
	    </style>
	    </head>
	    <body>
	    	<h1>Choose a Product to Edit to </h1>
	        <table> 
	        	<thead> 
			        <th>Category Id</th> 
			        <th>Category Name</th> 
			        <th>Category Description</th> 
			        <th>Category Image</th> 
			   	</thead>
	        <tbody>
	       <%  
			Connection connection=(Connection) getServletContext().getAttribute("db");
	        PreparedStatement pst = connection.prepareStatement("select * from categories");
	        ResultSet result = pst.executeQuery();
	        while (result.next()) { 
	        %>
	            <tr> 
	            <td><%= result.getString(1) %></td>
	            <td> <a style='text-decoration:none; color:black' href=SelectProduct.jsp?cid=<%= result.getString(1)%> > <%= result.getString(2) %> </a></td>
	            <td><%= result.getString(3) %></td>
	            <td><%= result.getString(4) %></td>
	            </tr>
	      <%   }
	      	result.close();
	      	pst.close();
	      %>
	        
	        
	        </tbody>
	        </table>

	    </body>
	    </html>