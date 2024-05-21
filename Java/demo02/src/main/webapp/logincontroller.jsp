<%@ page import="com.example.demo02.LoginBean" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/10/2024
  Time: 11:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login controller</title>
    <%
        //Step 1: Call controller
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //Step 2: Call instance of javabean(model)
        LoginBean loginBean = new LoginBean();
        boolean status = loginBean.checkLogin(username, password);
        if (status) {%>
    <jsp:forward page="success.jsp"/>
    <%} else {%>
    <jsp:forward page="fail.jsp"/>
    <%}%>
</head>
<body>

</body>
</html>