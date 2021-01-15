package Servlets;

import DAO.ReservationDAO;
import Entities.Reservation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "cancelReservation", value = "/cancelReservation")
public class cancelReservation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String reservationID = request.getParameter("ReservationID");
        ReservationDAO reservationDAO = new ReservationDAO();
        reservationDAO.cancelReservation(reservationID);//here
        response.sendRedirect(request.getContextPath() + "/Reservations.jsp");
    }
}
