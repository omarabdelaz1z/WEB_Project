package Servlets;

import DAO.ReservationDAO;
import Entities.Reservation;
import Entities.StaffMember;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "StaffReservationController", value = "/StaffReservationController")
public class StaffReservationController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        StaffMember staffMember = (StaffMember) session.getAttribute("staffMember");

        String staffID = staffMember.getID();

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String day = request.getParameter("dayOfWeek");

        List<Reservation> reservations = new ReservationDAO().getReservations("SELECT R From Reservation R INNER JOIN OfficeHour O " +
                "ON R.staffID = O.staffMemberID " + "WHERE O.dayOfWeek = '" + day + "' AND R.ID = '" + staffID +"' AND (O.startTime <= '"+ startTime + "' OR O.endTime < '" + endTime + "')");


        // DisplayReservations
        if(!reservations.isEmpty()){
            request.setAttribute("requestedReservations", reservations);
        }

        else{

        }
    }
}
