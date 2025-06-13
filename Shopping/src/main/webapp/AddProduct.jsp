<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
</head>
<body>
    <form action="AddProduct" method="post">
        Category:
        <select name="catID">
        <%
            try {
    			Connection connection=(Connection) getServletContext().getAttribute("db");
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM categories");

                while (rs.next()) {
        %>
                    <option value="<%= rs.getString("catId") %>"><%= rs.getString("catName") %></option>
        <%
                } 

                rs.close();
                statement.close();
            } catch (Exception e) {
        %>
                <option disabled>Error loading categories</option>
        <%
                e.printStackTrace();
            }
        %>
        </select>
        <br>
        Product ID : <input type="number" name="prodId"> <br>
        Product Name : <input type="text" name="prodName"> <br>
        Product Price : <input type="number" name="prodPrice"> <br>
        Product Image URL : <input type="text" name="prodImg"> <br>
        <input type="submit" value="Submit"> <br>
    </form>	
</body>
</html>
