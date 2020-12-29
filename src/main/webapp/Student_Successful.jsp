<%@ page import="Entities.Student" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Login Success Page</title>
</head>

<%
    Student student = (Student) session.getAttribute("currentStudent");

    if(student == null)

%>

<body>
<h3>Hi <%= student.getName() %>, Login successful.</h3>
<a href="login.html">Login Page</a>
</body>
</html>