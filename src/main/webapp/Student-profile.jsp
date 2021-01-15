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
    UserDAO userDAO = new UserDAO();
    Student student = (Student) session.getAttribute("currentUser");
    User student1 = userDAO.getUserbyID("17");
%>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<%
    out.print("<h2>Name: " + student1.getName() + "</h2>");
    out.print("<h2>Email: " + student1.getEmail() + "</h2>");
%>
<button onclick="window.location.href='Edit-student-profile.jsp';">EditProfile</button>
</body>
</html>
