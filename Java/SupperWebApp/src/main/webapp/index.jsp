<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<h2>
    <%--    cho phép nhúng code java trong html tags--%>
    <%
        java.util.Date date = new Date();
    %>

    Now is:<%=date.toString()%>
</h2>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>