<%@ page import="Entities.Student" %>
<%@ page import="Entities.StaffMember" %>
<%@ page import="java.util.List" %>
<%@ page import="Entities.User" %>
<%@ page import="DAO.UserDAO" %>
<%@ page import="DAO.ReservationDAO" %>
<%@ page import="Entities.Reservation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //access the var from the home
    UserDAO userDAO = new UserDAO();
    String id = (String) session.getAttribute("contactID");
    User user = userDAO.getUserbyID(id);
%>
<html>
<head>
    <title>Contact</title>
</head>
<body>
<h1>TEST</h1>
<%
    out.print("Contact name: " + user.getName());
    out.print("Contact email: " + user.getEmail());
%>
</body>
</html>
