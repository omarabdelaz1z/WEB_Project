package Servlets;

import DAO.NotificationDAO;
import DAO.ReservationDAO;
import Entities.Notification;
import Entities.Reservation;
import Entities.StaffMember;
import Entities.User;
import Utils.MailManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CancelAllAppointments", value = "/CancelAllAppointments")
public class CancelAllAppointments extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<User> users = (List<User>) session.getAttribute("Reservees");
        StaffMember staffMember = (StaffMember) session.getAttribute("currentUser");
        List<Reservation> reservations = staffMember.getReservations();

        MailManager mailManager = new MailManager();
        ReservationDAO reservationDAO = new ReservationDAO();
        List<Reservation> cancelledReservations = new ArrayList<>();

        for(int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i).setStatus(false);
            reservationDAO.cancelReservation(reservation.getID(), reservation);
            cancelledReservations.add(reservation);

            String subject = "Cancelled Meeting";
            String content = "Dear " + users.get(i).getName() + ",\nStudent " + staffMember.getName() + " has cancelled the meeting.";
            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            mailManager.sendEmail(users.get(i).getEmail(), subject, content);

            Notification notification = new NotificationDAO().createNotification(new Notification(users.get(i).getID(), staffMember.getID(), subject, content, currentDate));
            staffMember.addNotification(notification);
        }

        staffMember.setReservations(cancelledReservations);

        session.setAttribute("currentUser", staffMember);
        response.sendRedirect("/Pages/Staffhome");
    }
}
