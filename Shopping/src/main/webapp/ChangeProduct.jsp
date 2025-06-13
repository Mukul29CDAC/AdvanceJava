<%@page import="com.cdac.object.Product"%>
<%@page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
</head>
<body>
    <%
    	
    	Product product=(Product)session.getAttribute("product");
	
    	
    %>
    <form action="UpdateProductTable" method="post">
        Category ID: <input type="number" name="catId" readonly value="<%= product.getCatId() %>"><br>
        Product ID: <input type="number" name="prodId" readonly value="<%= product.getProdId() %>"><br>
        Product Name: <input type="text" name="prodName" value="<%= product.getProdName() %>"><br>
        Product Price: <input type="number" name="prodPrice" value="<%= product.getProdPrice()%>"><br>
        Product Image URL: <input type="text" name="prodImg" value="<%= product.getProdImg()%>"><br>
        <input type="submit" value="Update Product">
    </form>
    
        
</body>
</html>


<%--  <%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="UpdateProductTable" method="post">
        
        <%
            
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/online_shopping", "root", "root");
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM products catid=? and prodid=?");

                if (rs.next()) {
        %>

        
        Category : <input type="number" name="catId" disabled value=<%= rs.getInt(1)%>>
        <br>
        Product ID : <input type="number" name="prodId" disabled value=<%= rs.getInt(2)%>> <br>
        Product Name : <input type="text" name="prodName" value=<%= rs.getInt(3)%>> <br>
        Product Price : <input type="number" name="prodPrice" value=<%= rs.getInt(4)%>> <br>
        Product Image URL : <input type="text" name="prodImg" value=<%= rs.getInt(5)%>> <br>
        <%} %>
        <input type="submit" value="Submit"> <br>
    </form>	
</body>
</html>

--%>