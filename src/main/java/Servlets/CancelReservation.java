package Servlets;

import DAO.NotificationDAO;
import DAO.ReservationDAO;
import DAO.UserDAO;
import Entities.Notification;
import Entities.Reservation;
import Entities.StaffMember;
import Entities.Student;
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

@WebServlet(name = "CancelReservation", value = "/CancelReservation")
public class CancelReservation extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        Student student = (Student) session.getAttribute("currentUser");
        StaffMember emp = (StaffMember) session.getAttribute("emp");
        List<Reservation> reservations = student.getReservations();
        String reservationID = request.getParameter("reservationID");

        int index = getReservation(reservations, reservationID);
        Reservation reservation = reservations.get(index);


        reservation.setStatus(false);
        new ReservationDAO().cancelReservation(reservationID, reservation);

        student.removeReservation(index);
        student.addReservation(reservation);

        String subject = "Cancelled Meeting";
        String content = "Dear " + emp.getName() + ",\nStudent " + student.getName() + " has cancelled the meeting.";
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        new MailManager().sendEmail(emp.getEmail(), subject, content);
        Notification notification = new NotificationDAO().createNotification(new Notification(emp.getID(), emp.getID(), subject, content, currentDate));
        emp.addNotification(notification);

        session.setAttribute("currentUser", student);
        response.sendRedirect("/Pages/Studenthome");
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
