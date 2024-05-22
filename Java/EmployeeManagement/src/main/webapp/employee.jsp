<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add/Update Employee</title>
</head>
<body>
<h2>Add/Update Employee</h2>
<form action="${pageContext.request.contextPath}/employees/<c:if test="${employee != null}">${employee.id}</c:if>/insert" method="post">
    <c:if test="${employee != null}">
        <input type="hidden" name="id" value="${employee.id}">
    </c:if>
    <label for="fullName">Full Name:</label>
    <input type="text" id="fullName" name="fullName" value="${employee != null ? employee.fullName : ''}" required><br><br>

    <label for="birthday">Birthday:</label>
    <input type="date" id="birthday" name="birthday" value="${employee != null ? employee.birthday : ''}" required><br><br>

    <label for="address">Address:</label>
    <input type="text" id="address" name="address" value="${employee != null ? employee.address : ''}" required><br><br>

    <label for="position">Position:</label>
    <input type="text" id="position" name="position" value="${employee != null ? employee.position : ''}" required><br><br>

    <label for="department">Department:</label>
    <input type="text" id="department" name="department" value="${employee != null ? employee.department : ''}" required><br><br>

    <button type="submit">Submit</button>
    <a href="${pageContext.request.contextPath}/employees/">Cancel</a>
</form>
</body>
</html>
