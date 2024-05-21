<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Management Application</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark">
        <div>
            <a href="#" class="navbar-brand">User Management App</a>
        </div>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form action="<c:if test='${user != null}'>update</c:if><c:if test='${user == null}'>insert</c:if>" method="post">
                <caption>
                    <h2><c:if test="${user != null}">Edit User</c:if><c:if test="${user == null}">Add New User</c:if></h2>
                </caption>
                <c:if test="${user != null}">
                    <input type="hidden" name="id" value="<c:out value='${user.id}'/>"/>
                </c:if>
                <fieldset class="form-group">
                    <label>Name</label>
                    <input type="text" value="<c:out value='${user != null ? user.name : ""}'/>" class="form-control" name="name" required="required">
                </fieldset>
                <fieldset class="form-group">
                    <label>Email</label>
                    <input type="text" value="<c:out value='${user != null ? user.email : ""}'/>" class="form-control" name="email" >
                </fieldset>
                <fieldset class="form-group">
                    <label>Country</label>
                    <input type="text" value="<c:out value='${user != null ? user.country : ""}'/>" class="form-control" name="country" >
                </fieldset>
                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
