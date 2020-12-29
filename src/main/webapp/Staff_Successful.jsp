<%@ page import="Entities.StaffMember" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Login Success Page</title>
</head>

<%
    StaffMember staffMember = (StaffMember) session.getAttribute("currentStaffMember");
%>

<body>
<h3>Hi <%= staffMember.getName() %>, Login successful.</h3>
<a href="login.html">Login Page</a>
</body>
</html>