package Servlets;

import DAO.ReservationDAO;
import Entities.Reservation;
import Entities.StaffMember;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReservationHistory", value= "/ReservationHistory")
public class ReservationHistory extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String staffID = ((StaffMember)(request.getSession().getAttribute("currentUser"))).getID();

        List<Reservation> reservations =
                new ReservationDAO().getReservations("SELECT R FROM Reservation R WHERE R.staffID = '" + staffID + "'");


        // Validation will be done in servlet.
        request.setAttribute("reservationHistory", reservations);
    }
}
