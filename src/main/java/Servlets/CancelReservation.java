package Servlets;

import DAO.ReservationDAO;
import Entities.Reservation;
import Entities.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CancelReservation", value = "/CancelReservation")
public class CancelReservation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Student student = (Student) session.getAttribute("currentUser");
        List<Reservation> reservations = student.getReservations();
        String reservationID = request.getParameter("reservationID");

        int index = getReservation(reservations, reservationID);
        Reservation reservation = reservations.get(index);

        reservation.setStatus(false);
        new ReservationDAO().cancelReservation(reservationID, reservation);

        student.removeReservation(index);
        student.addReservation(reservation);

        session.setAttribute("currentUser", student);
        request.getRequestDispatcher("").forward(request, response);
    }

    private int getReservation(List<Reservation> reservationList, String ID) {
        for(int i = 0; i < reservationList.size(); i++){
            if(reservationList.get(0).getID().equalsIgnoreCase(ID)){
                return i;
            }
        }
        return -1;
    }

}
