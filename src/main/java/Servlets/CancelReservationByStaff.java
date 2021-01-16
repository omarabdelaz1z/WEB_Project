package Servlets;

import DAO.NotificationDAO;
import DAO.ReservationDAO;
import Entities.Notification;
import Entities.Reservation;
import Entities.StaffMember;
import Utils.MailManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CancelReservationByStaff", value = "/CancelReservationByStaff")
public class CancelReservationByStaff extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String reservationID = request.getParameter("reservationID");
        String reserveeID = request.getParameter("reserveeID");
        String studentName = request.getParameter("studentName");
        String studentEmail = request.getParameter("studentEmail");

        StaffMember staffMember = (StaffMember) session.getAttribute("currentUser");

        List<Reservation> reservations = staffMember.getReservations();

        int index = getReservation(reservations, reservationID);
        Reservation reservation = reservations.get(index);

        reservation.setStatus(false);
        new ReservationDAO().cancelReservation(reservationID, reservation);

        staffMember.removeReservation(index);
        staffMember.addReservation(reservation);

        String subject = "Cancelled Meeting";
        String content = "Dear " + studentName + ",\nStudent " + staffMember.getName() + " has cancelled the meeting.";
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        new MailManager().sendEmail(studentEmail, subject, content);
        Notification notification = new NotificationDAO().createNotification(new Notification(reserveeID, staffMember.getID(), subject, content, currentDate));
        staffMember.addNotification(notification);

        session.setAttribute("currentUser", staffMember);
        response.sendRedirect("/Pages/Staffhome");
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
