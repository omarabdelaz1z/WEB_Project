package Servlets;

import DAO.ReservationDAO;
import DAO.UserDAO;
import Entities.Reservation;
import Entities.StaffMember;
import Entities.Student;
import Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ReservationHistory", value= "/ReservationHistory")
public class ReservationHistory extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String staffID = ((StaffMember)(request.getSession().getAttribute("currentUser"))).getID();

        List<Reservation> reservations =
                new ReservationDAO().getReservations("SELECT R FROM Reservation R WHERE R.staffID = '" + staffID + "'");

        if(reservations.isEmpty()){

        }
        else{
            UserDAO userDAO = new UserDAO();
            List<User> users = new ArrayList<>();

            for(Reservation reservation: reservations){
                User user = userDAO.getUserByID(reservation.getReserveeID());
                users.add(user);
            }

            request.setAttribute("reservationHistory", reservations);
            request.setAttribute("students", users);
        }
    }

    public static void main(String[] args) {
        String staffID = "1";

        List<Reservation> reservations =
                new ReservationDAO().getReservations("SELECT R FROM Reservation R WHERE R.staffID = '" + staffID + "'");

        if (!reservations.isEmpty()) {
            UserDAO userDAO = new UserDAO();
            List<User> users = new ArrayList<>();

            for (Reservation reservation : reservations) {
                User user = userDAO.getUserByID(reservation.getReserveeID());
                users.add(user);
            }
            System.out.println(reservations);
            System.out.println(users);
        }
    }
}
