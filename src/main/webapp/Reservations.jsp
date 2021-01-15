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
    Student student = (Student) session.getAttribute("currentUser");
    UserDAO userDAO = new UserDAO();
    ReservationDAO reservationDAO = new ReservationDAO();
    List<Reservation> reservations = reservationDAO.getUserReservations("17");
%>
<html>
<head>
    <title>Reservations</title>
</head>
<body>
<%
    if (reservations.isEmpty()) {
        out.print("<h1>You have no reservations yet</h1>");
    } else {
        for (Reservation ob : reservations) {
            if (ob.isStatus()) {
                User staff = userDAO.getUserbyID(ob.getStaffID());
                out.print("<h3>From: " + ob.getFromDate() + "</h3>");
                out.print("<h3>To: " + ob.getToDate() + "</h3>");
                out.print("<h3>With: " + staff.getName() + "</h3>");
                out.print("<form action=\"cancelReservation\" method=\"POST\">");
                out.print("<input type=\"hidden\" name=\"ReservationID\" value=\"" + ob.getID() + "\">");
                out.print("<input type=\"submit\" value=\"Cancel\">");
                out.print("</form>");
                out.print("<hr>");
            }
        }
    }
%>
</body>
</html>
