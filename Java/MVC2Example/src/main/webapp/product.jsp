<%@ page import="com.example.mvc2example.Product" %>
<%@ page import="java.util.List" %>
<%--<%@ page contentType="text/html; ISO-8859-1" language="java" %>--%>
<%--Created by IntelliJ IDEA.
  User: Admin
  Date: 5/17/2024
  Time: 9:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Management</title>
    <h1>Product List</h1>
<%-- Step 4: load data from model --%>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Action</th>
        </tr>
<%-- Jsp Skiplets: Nhúng code java trong html page --%>
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            if (products != null) {
                for (Product product : products) {
        %>
            <tr>
                <td><%= product.getId()%></td>
                <td><%= product.getName()%></td>
                <td><%= product.getPrice()%></td>
                <td>
                    <a href="ProductController?action=delete&id=<%= product.getId()%>">Delete</a>
                </td>
            </tr>
        <%        }
            }
        %>
    </table>

<br/>
<form action="ProductController" method="post">
    <input type="hidden" name="action" value="add">
    Name :<input type="text" name="name"><br/>
    Price :<input type="text" name="price"><br/>
    <input type="submit" value="Add Product">
</form>
</head>
<body>

</body>
</html>
